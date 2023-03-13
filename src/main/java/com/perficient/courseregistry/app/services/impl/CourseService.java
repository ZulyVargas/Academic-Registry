package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {

    private final ICourseRepository courseRepository;
    private final ICourseMapper courseMapper;

    public CourseService(ICourseRepository courseRepository, ICourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    @Override
    public Set<CourseDTO> getAllCourses(Integer limit, Integer offset, Optional<Boolean> isActive) {
        return courseRepository.findAll(limit, offset, isActive.orElse(true)).stream().map(s -> courseMapper.courseToCourseDTO(s)).collect(Collectors.toSet());
    }

    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        UUID idNewCourse = UUID.randomUUID();
        courseDTO.setCourseId(idNewCourse);
        Boolean isSaved = courseRepository.save(courseDTO.getCourseId(), courseDTO.getGroupNumber(), courseDTO.getQuota(),
                courseDTO.getProfessor().getUserId(), courseDTO.getSubject().getSubjectId(),
                courseDTO.getStatusCourse(), courseDTO.getYear(),
                courseDTO.getPeriod(), courseDTO.isActive());
        if (isSaved) return getCourseById(String.valueOf(idNewCourse));
        else{
            throw new SubjectException(CourseException.COURSE_INSERT_EXCEPTION, "ID");
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
}