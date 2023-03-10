package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.SubjectDTO;

import java.util.Optional;
import java.util.Set;

public interface ISubjectService {
    Set<SubjectDTO> getAllSubjects(Integer limit, Integer offset, Optional<Boolean> isActive);
    SubjectDTO getSubjectByTitle(String title);
    SubjectDTO getSubjectById(String id);
    SubjectDTO addSubject(SubjectDTO subjectDTO);
    SubjectDTO updateSubject(SubjectDTO subjectDTO);
    boolean deleteSubject(String subjectId);
}