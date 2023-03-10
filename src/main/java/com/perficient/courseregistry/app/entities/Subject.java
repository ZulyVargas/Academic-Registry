package com.perficient.courseregistry.app.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Set;
import java.util.UUID;

@Data
@Table(value="subjects")
public class Subject {

    @Column("subject_id")
    private @Id UUID subjectId;
    private  String title;
    private String code;
    private Integer credits;
    @Column("active_subject")
    private boolean active;
    @Transient
    private Set<Subject> prerrequisites;


}