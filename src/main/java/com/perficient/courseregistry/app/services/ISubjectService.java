package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import java.util.List;
import java.util.Optional;

public interface ISubjectService {
    List<SubjectDTO> getAllSubjects(Integer limit, Integer offset, Optional<Boolean> isActive, Optional<String> title );
    List<SubjectDTO> getSubjectByTitle(Integer limit, Integer offset, boolean isActive, String title);
    SubjectDTO getSubjectById(String id);
    SubjectDTO addSubject(SubjectDTO subjectDTO);
    SubjectDTO updateSubject(SubjectDTO subjectDTO);
    boolean deleteSubject(String subjectId);
}