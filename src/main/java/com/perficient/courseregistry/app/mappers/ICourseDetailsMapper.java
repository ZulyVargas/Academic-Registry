package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper
public interface ICourseDetailsMapper {
    CourseDTO courseDetailsToCourseDTO(CourseDetails courseDetails);

    @Mapping(source = "professor", target = "professorId", qualifiedByName = "getIdProfessor")
    @Mapping(source = "subject", target = "subjectId", qualifiedByName = "getIdSubject")
    CourseDetails  courseDTOToCourseDetails(CourseDTO courseDTO);

    @Named("getIdProfessor")
    default UUID getIdProfessor(ProfessorDTO professorDTO) {
        return professorDTO.getUserId();
    }
    @Named("getIdSubject")
    default UUID getIdSubject(SubjectDTO subjectDTO) {
        return subjectDTO.getSubjectId();
    }
}
