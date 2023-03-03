package com.perficient.courseregistry.app.entities;

import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
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
