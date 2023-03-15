package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Student;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStudentRepository extends CrudRepository<Student, String> {

    @Query("SELECT * FROM INFO_STUDENTS WHERE active_user OR active_user=:isActive LIMIT :limit OFFSET :offset")
    List<Student> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isActive") boolean isActive );


    @Query("SELECT * FROM INFO_STUDENTS WHERE user_id = :studentId")
    Optional<Student> findById(UUID studentId );

    @Modifying
    @Query("INSERT INTO STUDENTS (student_id, avg, status) VALUES (:idStudent, :avg, :status)")
    Boolean save(UUID idStudent, double avg, STATUS_STUDENT status);

    @Modifying
    @Query("UPDATE STUDENTS SET avg=:avg, status =:status WHERE student_id = :userId")
    Boolean update(UUID userId, double avg, STATUS_STUDENT status);
}
