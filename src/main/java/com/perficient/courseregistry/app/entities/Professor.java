package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@Table(value="professors")
public class Professor extends User {

    @Column("professor_id")
    private @Id UUID professorId;
    private String degree;

}
