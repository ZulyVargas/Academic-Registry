package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Course;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ICourseRepository extends CrudRepository<Course,String> {

    @Query("SELECT * FROM COURSES WHERE group_number= :groupNumber" )
    Course findByGroupNumber(@Param("groupNumber") String groupNumber);

}
