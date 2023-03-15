package com.perficient.courseregistry.app.utils.adapters.entities;

import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Data
@Builder
@Table(value = "courses")
public class CourseDetails {
    @Column("course_id")
    private @Id UUID courseId;
    @Column("group_number")
    private String groupNumber;
    private Integer quota;
    @Column("professor_id")
    private UUID professorId;
    @Column("subject_id")
    private UUID subjectId;
    @Column("status_course")
    private STATUS_COURSE statusCourse;
    private String year;
    private PERIOD period;
    private boolean active_course;
}
