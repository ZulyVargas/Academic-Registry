package com.perficient.courseregistry.app.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@SuperBuilder
@Data
@Table(value="professors")
public class Professor extends User {
    private String degree;

}
