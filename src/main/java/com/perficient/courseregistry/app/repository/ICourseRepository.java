package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;

@Repository
public interface ICourseRepository extends CrudRepository<Course,String> {

    @Query("SELECT * FROM INFO__TOTAL_COURSES WHERE active_course OR active_course=:isActive LIMIT :limit OFFSET :offset")
    Set<Course> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isActive") boolean isActive);

}
