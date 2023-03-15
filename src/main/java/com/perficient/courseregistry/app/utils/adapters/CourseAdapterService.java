package com.perficient.courseregistry.app.utils.adapters;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.mappers.ICourseDetailsMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CourseAdapterService implements ICourseAdapterService {

    private final ICourseDetailsMapper courseDetailsMapper;

    public CourseAdapterService(ICourseDetailsMapper courseDetailsMapper) {
        this.courseDetailsMapper = courseDetailsMapper;
    }

    @Override
    public Optional<Course> saveWithDatabaseFormat(CourseDTO dtoObject, ICourseRepository courseRepository) {
        CourseDetails courseSaved = courseRepository.save(courseDetailsMapper.courseDTOToCourseDetails(dtoObject));
        return courseRepository.findById(courseSaved.getCourseId());
    }

    @Override
    public Optional<Course> updateWithDatabaseFormat(CourseDTO dbObject, ICourseRepository courseRepository) {
        CourseDetails courseUpdated= courseRepository.save(courseDetailsMapper.courseDTOToCourseDetails(dbObject));
        return courseRepository.findById(courseUpdated.getCourseId());
    }

}
