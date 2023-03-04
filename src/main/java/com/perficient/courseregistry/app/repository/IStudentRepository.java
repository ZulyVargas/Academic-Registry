package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Student;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface IStudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT * FROM INFO_STUDENTS")
    Set<Student> findAll();
}