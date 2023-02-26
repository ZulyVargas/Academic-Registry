package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.mappers.ISubjectMapper;
import com.perficient.courseregistry.app.repository.ISubjectRepository;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService  implements ISubjectService {

    @Autowired
    private final ISubjectRepository subjectRepository;

    public SubjectService(ISubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    private Set<Subject> findPrerrequisitesById(UUID subjectId) {
        Set<Subject> prerrequisites = this.subjectRepository.findPrerrequisitesById(subjectId).stream()
                                                                                              .map(p -> {   p.setPrerrequisites(findPrerrequisitesById(p.getSubjectId()));
                                                                                                            return p; }
                                                                                              ).collect(Collectors.toSet());
        return prerrequisites;
    }

    private Set<SubjectDTO> groupSubjects(List<Subject> subjectList){
        Set<SubjectDTO> subjects = subjectList.stream()
                                               .map(s -> {    s.setPrerrequisites(this.findPrerrequisitesById(s.getSubjectId()));
                                                              SubjectDTO subjectDTO = ISubjectMapper.INSTANCE.subjectToSubjectDTO(s);
                                                              return subjectDTO; }
                                                         ).collect(Collectors.toSet());
        return subjects;

    }

    @Override
    public Set<SubjectDTO> getAllSubjects() {
        return groupSubjects(subjectRepository.findAll());
    }

    @Override
    public Set<SubjectDTO> getAllSubjectsPaged(Integer limit, Integer offset) {
        return groupSubjects(this.subjectRepository.findAllPageable(limit, offset));
    }

    @Override
    public SubjectDTO getSubjectByTitle(String title) {
        Subject subject = this.subjectRepository.findByTitle(title);
        subject.setPrerrequisites(findPrerrequisitesById(subject.getSubjectId()));
        SubjectDTO subjectDto = ISubjectMapper.INSTANCE.subjectToSubjectDTO(subject);
        return subjectDto;
    }

    @Override
    public SubjectDTO getSubjectById(String id) {
        Subject subject = this.subjectRepository.findById(UUID.fromString(id));
        subject.setPrerrequisites(findPrerrequisitesById(subject.getSubjectId()));
        SubjectDTO subjectDto = ISubjectMapper.INSTANCE.subjectToSubjectDTO(subject);
        return subjectDto;
    }

    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        Subject newSubject = subjectRepository.save(ISubjectMapper.INSTANCE.subjectDtoToSubject(subjectDTO));
        return ISubjectMapper.INSTANCE.subjectToSubjectDTO(newSubject);
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
        return this.addSubject(subjectDTO);
    }

    @Override
    public Boolean deleteSubject(String subjectId) {
        this.subjectRepository.deleteById(subjectId);
        return true;
    }

}
