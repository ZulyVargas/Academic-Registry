package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;
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
        System.out.println("RESULT  " + courseRepository.save(courseDTO.getGroupNumber(), courseDTO.getQuota(),
                courseDTO.getProfessor().getUserId(), courseDTO.getSubject().getSubjectId(),
                courseDTO.getStatusCourse(), courseDTO.getYear(),
                courseDTO.getPeriod(), courseDTO.isActive()));
        return null;

        //return courseMapper.courseToCourseDTO(courseRepository.save(courseMapper.courseDTOToCourse(courseDTO)));
    }

}
