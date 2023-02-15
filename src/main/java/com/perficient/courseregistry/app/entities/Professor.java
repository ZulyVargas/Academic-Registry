package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

@Getter
@Setter
@SuperBuilder
public class Professor extends User{
    @Column("student_id")
    private String professorId;
    private String degree;

}
