package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Set;

public interface ISubjectService {

    Set<SubjectDTO> getAllSubjects();
    Set<SubjectDTO> getAllSubjectsPaged(Integer limit, Integer offset);

    SubjectDTO getSubjectByTitle(String title);

    SubjectDTO getSubjectById(String id);

    SubjectDTO addSubject(SubjectDTO subjectDTO);

    SubjectDTO updateSubject(SubjectDTO subjectDTO);

    Boolean deleteSubject(String subjectId);
}
