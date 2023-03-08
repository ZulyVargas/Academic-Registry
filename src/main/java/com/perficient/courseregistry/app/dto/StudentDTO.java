package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Data
@SuperBuilder
public class StudentDTO extends UserDTO implements Serializable {
    private double avg;
    private STATUS_STUDENT status;
    private Set<RecordDTO> records ;
}
