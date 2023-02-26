package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;

import java.util.List;

public interface ICourseService {
    List<CourseDTO> getAllCourses();

    List<Course> getAllCoursesPaged(Integer limit, Integer details);

    Course saveCourse(Course course);
}
