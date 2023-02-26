package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends CrudRepository<Course,String> {

    @Query("SELECT * FROM COURSES WHERE active and status_course='IN_PROGRESS' ")
    List<Course> findAll();

    @Query("SELECT * FROM COURSES WHERE active and status_course='IN_PROGRESS' LIMIT :limit OFFSET :offset")
    List<Course> findAllPageable(Integer limit, Integer offset);


}
