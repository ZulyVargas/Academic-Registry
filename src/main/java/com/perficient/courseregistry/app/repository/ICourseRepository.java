package com.perficient.courseregistry.app.repository;

import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ICourseRepository extends CrudRepository<Course,String> {

    @Query("SELECT * FROM INFO__TOTAL_COURSES WHERE active_course OR active_course=:isActive LIMIT :limit OFFSET :offset")
    Set<Course> findAll(@Param("limit") Integer limit, @Param("offset") Integer offset, @Param("isActive") boolean isActive);

    @Modifying
    @Query("INSERT INTO COURSES (group_number, quota, professor_id, subject_id, status_course, year, period, active_course) VALUES" +
            "(:groupNumber, :quota, :professorId, :subjectId,  :statusCourse, :year, :period, :active )")
    boolean save(@Param("groupNumber") String groupNumber, @Param("quota") Integer quota, @Param("professorId") UUID professorId,
                 @Param("subjectId") UUID subjectId,  @Param("statusCourse") STATUS_COURSE statusCourse, @Param("year") String year,
                 @Param("period") PERIOD period,  @Param("active") boolean active);
}
