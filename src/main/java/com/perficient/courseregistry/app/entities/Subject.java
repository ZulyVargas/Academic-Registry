package com.perficient.courseregistry.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Column;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class Subject {

    @Column("subject_id")
    private UUID subjectId;
    private  String title;
    private String code;
    private String credits;
    private Set<Subject> prerrequisites;

}