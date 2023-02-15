package com.perficient.courseregistry.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@AllArgsConstructor
public class Record {
    @Column("course_id")
    private String courseId;
    private double grade;


}
