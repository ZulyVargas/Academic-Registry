package com.perficient.courseregistry.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
public class Course {
    @Column("course_id")
    private @Id String courseId;
    @Column("group_number")
    private String groupNumber;
    private String quota;
    private Professor professor;
    private Subject subject;

}

