package com.perficient.courseregistry.app.entities;

import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Course {

    @Column("course_id")
    private @Id UUID courseId;
    @Column("group_number")
    private String groupNumber;
    private Integer quota;
    private Professor professor;
    private Subject subject;
    @Column("status_course")
    private STATUS_COURSE statusCourse;
    private String year;
    private PERIOD period;
    private boolean active;
}

