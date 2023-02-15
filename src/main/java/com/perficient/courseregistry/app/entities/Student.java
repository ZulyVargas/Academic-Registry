package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;

@Getter
@Setter
@SuperBuilder
public class Student extends User{
    private String studentId;
    private double average;
    @MappedCollection(idColumn = "student_id")
    private Set<Record> records ;


}
