package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Subject;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ISubjectRepository extends CrudRepository<Subject,String> {

    @Query("SELECT * FROM SUBJECTS WHERE active")
    Set<Subject> findAll();

    @Query("SELECT * FROM SUBJECTS WHERE active LIMIT :limit OFFSET :offset")
    Set<Subject> findAllPageable(@Param("limit") Integer limit, @Param("offset") Integer offset);

    @Query("SELECT subject_id, title, code, credits, active FROM PREREQUISITES_INFO WHERE base_id=:subjectId AND active")
    Set<Subject> findPrerrequisitesById(@Param("subjectId") UUID subjectId);

    Subject findByTitle(@Param("title") String title);

}