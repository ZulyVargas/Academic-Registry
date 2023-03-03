package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.entities.Record;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

public class StudentDTO extends UserDTO implements Serializable {
    private UUID studentId;
    private double avg;
    private STATUS_STUDENT status;
    private Set<Record> records ;
}
