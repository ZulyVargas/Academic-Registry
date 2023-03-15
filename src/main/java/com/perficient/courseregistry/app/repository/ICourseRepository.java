package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ICourseRepository extends CrudRepository<Course,UUID> {

    @Query("SELECT * FROM INFO__TOTAL_COURSES WHERE active_course OR active_course=:isActive LIMIT :limit OFFSET :offset")
    Set<Course> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isActive") boolean isActive);

    @Query("SELECT * FROM INFO__TOTAL_COURSES WHERE course_id = :courseId")
    Optional<Course> findById(@Param("courseId") UUID courseId);

    CourseDetails save(CourseDetails course);

    @Modifying
    @Query("UPDATE courses SET active_course=false WHERE course_id = :courseId")
    boolean updateActive(@Param("courseId") UUID courseId);
}
