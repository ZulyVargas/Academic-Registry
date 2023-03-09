package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface ISubjectMapper {

    SubjectDTO subjectToSubjectDTO(Subject subject);
    Subject subjectDtoToSubject(SubjectDTO subjectDTO);
}