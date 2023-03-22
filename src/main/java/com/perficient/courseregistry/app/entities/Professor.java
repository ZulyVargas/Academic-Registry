package com.perficient.courseregistry.app.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Table;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(value="professors")
public class Professor extends User {
    private String degree;

}