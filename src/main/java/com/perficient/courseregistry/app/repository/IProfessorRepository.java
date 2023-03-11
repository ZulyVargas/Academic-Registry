package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.UUID;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor, String> {
    @Query("SELECT * FROM INFO_PROFESSORS WHERE active_user OR active_user=:isActive LIMIT :limit OFFSET :offset")
    Set<Professor>findAll(@Param("limit") Integer limit,@Param("offset") Integer offset, @Param("isActive") boolean isActive );

    @Query("SELECT * FROM INFO_PROFESSORS where degree=:degree")
    Set<Professor> findAllByDegree(@Param("degree") String degree);

    @Modifying
    @Query("INSERT INTO PROFESSORS (professor_id, degree) VALUES (:idProfessor, :degree)")
    Boolean save(UUID idProfessor, String degree);

}
