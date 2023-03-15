package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.services.ICourseService;
import com.perficient.courseregistry.app.utils.adapters.ICourseAdapterService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ICourseMapper courseMapper;
    private final ICourseAdapterService courseAdapter;

    public CourseService(ICourseRepository courseRepository, ICourseMapper courseMapper, ICourseAdapterService courseAdapter) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
        this.courseAdapter = courseAdapter;
    }

    @Override
    public List<CourseDTO> getAllCourses(Integer limit, Integer offset, Optional<Boolean> isActive) {
        return courseRepository.findAll(limit, offset, isActive.orElse(true)).stream().map(s -> courseMapper.courseToCourseDTO(s)).collect(Collectors.toList());
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        try{
            Optional<Course> courseSaved = courseAdapter.saveWithDatabaseFormat(courseDTO, courseRepository);
            return courseSaved.map(courseMapper::courseToCourseDTO).orElseThrow();
        }
        catch(Exception e){
            throw new CourseException(CourseException.COURSE_INSERT_EXCEPTION, "ID");
        }
    }

    @Override
    public CourseDTO getCourseById(String id) {
        Optional<Course> course = courseRepository.findById(UUID.fromString(id));
        if (course.isPresent()) return courseMapper.courseToCourseDTO(course.get());
        else {
           throw new CourseException(CourseException.COURSE_ID_EXCEPTION, "ID");
        }
    }

    @Override
    public boolean deleteCourse(String courseId) {
        try{
            Optional<Course> course = courseRepository.findById(UUID.fromString(courseId));
            if (course.isEmpty() || !course.get().isActive()) return false;
            return courseRepository.updateActive(UUID.fromString(courseId));
        }catch (Exception ex){
            throw new CourseException(CourseException.COURSE_DELETE_EXCEPTION, "ID");
        }
    }
}