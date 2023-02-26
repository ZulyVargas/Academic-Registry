package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private final ICourseRepository courseRepository;
    //private final ICourseMapper courseMapper;

    public CourseService(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseDTO> getAllCourses() {
        List<CourseDTO> courses = courseRepository.findAll().stream().map(s -> ICourseMapper.INSTANCE.courseToCourseDTO(s)).collect(Collectors.toList());

        return courses;
    }

    @Override
    public List<Course> getAllCoursesPaged(Integer limit, Integer offset) {
        return courseRepository.findAllPageable(limit, offset);
    }

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }


}
