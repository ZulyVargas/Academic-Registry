package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Subject;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ISubjectRepository extends CrudRepository<Subject,String> {

    @Query("SELECT * FROM SUBJECTS WHERE code = :code")
    Subject findByCode(@Param("code") String code);

    @Query("SELECT * FROM SUBJECTS WHERE title = :title")
    Subject findByTitle(@Param("title") String title);

    @Query("INSERT INTO PREREQUISITES VALUES(:subjectId,:prerequisiteId)")
    void savePrerequisites(@Param("subjectId") String subjectId, @Param("prerequisiteId") String prerequisiteId);
}
