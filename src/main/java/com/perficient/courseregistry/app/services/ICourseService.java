package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.CourseDTO;
import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<CourseDTO> getAllCourses(Integer limit, Integer offset, Optional<Boolean> isActive);

    CourseDTO addCourse(CourseDTO courseDTO);

    CourseDTO getCourseById(String id);
}
