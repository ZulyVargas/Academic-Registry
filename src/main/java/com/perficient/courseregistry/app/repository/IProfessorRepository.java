package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.Student;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProfessorRepository extends CrudRepository<Professor, String> {
    @Query("SELECT  * FROM  USERS u JOIN PROFESSORS s ON u.user_id = s.professor_id  WHERE u.username = :username")
    Student findByUsername(@Param("username") String username);
}
