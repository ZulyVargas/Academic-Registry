package com.perficient.courseregistry.app.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.servlet.annotation.WebFilter;
import java.sql.ResultSet;

public interface ICourseRepository<Course> extends CrudRepository<Course,String> {

    @Query("SELECT * FROM COURSES where group_number= :groupNumber" )
    Course findByGroupNumber(@Param("groupNumber") String groupNumber);
    

}
