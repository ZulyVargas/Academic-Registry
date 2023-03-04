package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;

import java.util.List;
import java.util.Set;

public interface ICourseService {
    Set<CourseDTO> getAllCourses();

    //Set<Course> getAllCoursesPaged(Integer limit, Integer details);

    //Course saveCourse(Course course);
}
