package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.UUID;

@Mapper
public  interface ICourseMapper {

    ICourseMapper INSTANCE= Mappers.getMapper(ICourseMapper.class);
    CourseDTO courseToCourseDTO(Course course);

    //Course   courseDTOToCourse(CourseDTO courseDTO);


}
