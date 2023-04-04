package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import org.mapstruct.Mapper;

@Mapper
public  interface ICourseMapper {

    CourseDTO courseToCourseDTO(Course course);
    Course   courseDTOToCourse(CourseDTO courseDTO);

}
