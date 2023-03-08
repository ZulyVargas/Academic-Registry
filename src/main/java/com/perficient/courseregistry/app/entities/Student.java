package com.perficient.courseregistry.app.entities;

import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Set;

@Data
@SuperBuilder
@Table(value="students")
public class Student extends User{
    private double avg;
    private STATUS_STUDENT status;
    @MappedCollection(idColumn = "student_id")
    private Set<Record> records;

}
