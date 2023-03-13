package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.CourseDTO;
import java.util.Optional;
import java.util.Set;

public interface ICourseService {
    Set<CourseDTO> getAllCourses(Integer limit, Integer offset, Optional<Boolean> isActive);

    CourseDTO addCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(String id);
}
