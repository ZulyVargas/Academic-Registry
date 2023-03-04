package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Professor;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor, String> {
    @Query("SELECT * FROM INFO_PROFESSORS WHERE active ")
    Set<Professor> findAll();

    @Query("SELECT * FROM INFO_PROFESSORS where degree=:degree")
    Set<Professor> findAllByDegree(@Param("degree") String degree);
}
