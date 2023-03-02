package com.perficient.courseregistry.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(value="subjects")
public class Subject {

    @Column("subject_id")
    private @Id UUID subjectId;
    private  String title;
    private String code;
    private String credits;
    @Transient
    private Set<Subject> prerrequisites;

}
