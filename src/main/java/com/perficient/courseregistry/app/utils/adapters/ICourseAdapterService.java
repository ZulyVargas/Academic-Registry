package com.perficient.courseregistry.app.utils.adapters;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.repository.ICourseRepository;

import java.util.Optional;

public interface ICourseAdapterService {

   Optional<Course> saveWithDatabaseFormat(CourseDTO dtoObject, ICourseRepository courseRepository);

}
