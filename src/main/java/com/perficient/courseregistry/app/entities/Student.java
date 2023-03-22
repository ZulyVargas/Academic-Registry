package com.perficient.courseregistry.app.entities;

import java.util.Set;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.MappedCollection;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(value="students")
public class Student extends User{
    private double avg;
    private STATUS_STUDENT status;
    @MappedCollection(idColumn = "student_id")
    private Set<Record> records;

}