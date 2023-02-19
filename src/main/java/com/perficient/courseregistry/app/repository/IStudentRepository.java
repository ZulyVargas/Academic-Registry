package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Student;
import com.perficient.courseregistry.app.entities.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IStudentRepository extends CrudRepository<User,String> {
    @Query("SELECT  * FROM  USERS u JOIN STUDENTS s ON u.user_id = s.student_id  WHERE u.username = :username")
    Student findByUsername(@Param("username") String username);

}
