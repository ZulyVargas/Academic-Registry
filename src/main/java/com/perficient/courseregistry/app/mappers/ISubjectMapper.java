package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper
public interface ISubjectMapper {


    ISubjectMapper INSTANCE = Mappers.getMapper(ISubjectMapper.class);
    SubjectDTO subjectToSubjectDTO(Subject subject);

    Subject subjectDtoToSubject(SubjectDTO subjectDTO);
}