package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Subject;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

@Repository
public interface ISubjectRepository extends CrudRepository<Subject,String> {

    @Query("SELECT * FROM SUBJECTS" )
    List<Subject> findAll();

    @Query("SELECT prerrequisite_id FROM PREREQUISITES_SUBJECT WHERE subject_id=:subjectId")
    List<UUID> findPrerrequisitesById(@Param("subjectId") UUID subjectId);

    @Query("SELECT * FROM SUBJECTS WHERE code = :code")
    Subject findByCode(@Param("code") String code);

    @Query("SELECT * FROM SUBJECTS WHERE title = :title")
    Subject findByTitle(@Param("title") String title);

    @Query("INSERT INTO PREREQUISITES VALUES(:subjectId,:prerrequisiteId)")
    void savePrerequisites(@Param("subjectId") String subjectId, @Param("prerequisiteId") String prerequisiteId);
}