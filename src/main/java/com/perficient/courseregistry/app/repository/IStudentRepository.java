package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.Student;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface IStudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT * FROM INFO_STUDENTS WHERE active OR active=:isActive LIMIT :limit OFFSET :offset")
    Set<Student>findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isActive") boolean isActive );
}
