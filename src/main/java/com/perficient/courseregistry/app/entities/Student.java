package com.perficient.courseregistry.app.entities;

import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
public class Student extends User{

    @Column("student_id")
    private UUID studentId;
    private double avg;
    private STATUS_STUDENT status;
    @MappedCollection(idColumn = "student_id")
    private Set<Record> records ;

}