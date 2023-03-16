package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.mappers.ISubjectMapper;
import com.perficient.courseregistry.app.repository.ISubjectRepository;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SubjectService  implements ISubjectService {

    private final ISubjectRepository subjectRepository;
    private final ISubjectMapper subjectMapper;

    public SubjectService(ISubjectRepository subjectRepository, ISubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public List<SubjectDTO> getAllSubjects(Integer limit, Integer offset, Optional<Boolean> isActive) {
        return groupSubjects(subjectRepository.findAll(limit, offset, isActive.orElse(true) ));
    }

    @Override
    public List<SubjectDTO> getSubjectByTitle(String title) {
        try{
            return groupSubjects(subjectRepository.findByTitle(title));
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
    public boolean deleteSubject(String subjectId) {
        try {
            Optional<Subject> subject = subjectRepository.findById(subjectId);
            if (subject.isEmpty() || !subject.get().isActive()) return false;
            return subjectRepository.updateActive(UUID.fromString(subjectId));
        } catch (Exception ex ) {
            throw new SubjectException(SubjectException.SUBJECT_DELETE_EXCEPTION, "ID");
        }
    }

    private List<SubjectDTO> groupSubjects(List<Subject> subjectList){
        return  subjectList.stream()
                .map(s -> { s.setPrerrequisites(this.findPrerrequisitesById(s.getSubjectId()));
                    return subjectMapper.subjectToSubjectDTO(s); })
                .collect(Collectors.toList());
    }

    private Set<Subject> findPrerrequisitesById(UUID subjectId) {
        return subjectRepository.findPrerrequisitesById(subjectId)
                .stream()
                .map(p -> { p.setPrerrequisites(findPrerrequisitesById(p.getSubjectId()));
                    return p; })
                .collect(Collectors.toSet());
    }

}
