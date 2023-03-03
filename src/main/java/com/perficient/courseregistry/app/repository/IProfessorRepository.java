package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Professor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor, String> {
    @Query("SELECT * FROM INFO_PROFESSORS")
    Set<Professor> findAll();
}
