package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Subject;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ISubjectRepository extends CrudRepository<Subject,String> {

    @Query("SELECT * FROM SUBJECTS WHERE active_subject OR active_subject=:isActive ORDER BY title LIMIT :limit OFFSET :initial ")
    List<Subject> findAll(@Param("limit") Integer limit, @Param("initial") Integer initial, @Param("isActive") boolean isActive);

    @Query("SELECT subject_id, title, code, credits, active_subject FROM PREREQUISITES_INFO WHERE base_id=:subjectId ORDER BY credits")
    Set<Subject> findPrerrequisitesById(@Param("subjectId") UUID subjectId);

    @Query("SELECT * FROM SUBJECTS WHERE title LIKE '%'||:title||'%' ORDER BY title" )
    List<Subject> findByTitle(@Param("title") String title);

    @Modifying
    @Query("UPDATE SUBJECTS SET active_subject = false WHERE subject_id=:subjectId")
    boolean updateActive(UUID subjectId);
}