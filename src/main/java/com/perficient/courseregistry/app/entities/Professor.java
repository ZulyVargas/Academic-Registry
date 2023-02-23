package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Professor extends User {

    @Column("professor_id")
    private UUID professorId;
    private String degree;

}