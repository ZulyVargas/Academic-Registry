package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.mappers.ISubjectMapper;
import com.perficient.courseregistry.app.repository.ISubjectRepository;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubjectService  implements ISubjectService {

    @Autowired
    private final ISubjectRepository subjectRepository;

    @Autowired
    private final ISubjectMapper subjectMapper;

    public SubjectService(ISubjectRepository subjectRepository, ISubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    private Set<Subject> findPrerrequisitesById(UUID subjectId) {
        Set<Subject> prerrequisites = this.subjectRepository.findPrerrequisitesById(subjectId)
                                                            .stream()
                                                            .map(p -> { p.setPrerrequisites(findPrerrequisitesById(p.getSubjectId()));
                                                                        return p; })
                                                            .collect(Collectors.toSet());
        return prerrequisites;
    }

    private Set<SubjectDTO> groupSubjects(List<Subject> subjectList){
        Set<SubjectDTO> subjects = subjectList.stream()
                                              .map(s -> { s.setPrerrequisites(this.findPrerrequisitesById(s.getSubjectId()));
                                                          return subjectMapper.subjectToSubjectDTO(s); })
                                              .collect(Collectors.toSet());
        return subjects;
    }

    @Override
    public Set<SubjectDTO> getAllSubjects() {
        List<Subject> array = StreamSupport.stream(subjectRepository.findAll().spliterator(), false)
                                           .collect(Collectors.toList());
        return groupSubjects(array);
    }

    @Override
    public Set<SubjectDTO> getAllSubjectsPaged(Integer limit, Integer offset) {
        return groupSubjects(this.subjectRepository.findAllPageable(limit, offset));
    }

    @Override
    public SubjectDTO getSubjectByTitle(String title) {
        try{
            Subject subject = this.subjectRepository.findByTitle(title);
            subject.setPrerrequisites(findPrerrequisitesById(subject.getSubjectId()));
            return subjectMapper.subjectToSubjectDTO(subject);
        }catch (Exception ex){
            throw new SubjectException(SubjectException.SUBJECT_TITLE_EXCEPTION , "title");
        }
    }

    @Override
    public SubjectDTO getSubjectById(String id) {
        Optional<Subject> subject = this.subjectRepository.findById(String.valueOf(UUID.fromString(id)));
        if (subject.isPresent()){
            subject.get().setPrerrequisites(findPrerrequisitesById(subject.get().getSubjectId()));
            return subjectMapper.subjectToSubjectDTO(subject.get());
        }else {
            throw new SubjectException(SubjectException.SUBJECT_ID_EXCEPTION, "ID");
        }

    }

    @Override
    public SubjectDTO addSubject(SubjectDTO subjectDTO) {
        try {
            return subjectMapper.subjectToSubjectDTO(subjectRepository.save(subjectMapper.subjectDtoToSubject(subjectDTO)));
        } catch (Exception ex ) {
            throw new SubjectException(SubjectException.SUBJECT_INSERT_EXCEPTION, "ID");
        }
    }

    @Override
    public SubjectDTO updateSubject(SubjectDTO subjectDTO) {
        try {
            return this.addSubject(subjectDTO);
        } catch (Exception ex ) {
            throw new SubjectException(SubjectException.SUBJECT_UPDATE_EXCEPTION,"ID");
        }
    }

    @Override
    public Boolean deleteSubject(String subjectId) {
        try {
            if (subjectRepository.findById(subjectId).isEmpty()) return false;
            this.subjectRepository.deleteById(subjectId);
            return true;
        } catch (Exception ex ) {
            throw new SubjectException(SubjectException.SUBJECT_DELETE_EXCEPTION, "ID");
        }
    }

}
