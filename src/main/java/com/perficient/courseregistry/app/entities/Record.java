package com.perficient.courseregistry.app.entities;

import com.perficient.courseregistry.app.enums.GRADE_TYPE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Table(value = "records")
public class Record {

    @Column("record_id")
    private @Id UUID recordId;
    @Column("course_id")
    private UUID courseId;
    private GRADE_TYPE type;
    private double grade;

}
