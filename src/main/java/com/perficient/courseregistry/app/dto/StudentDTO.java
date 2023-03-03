package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends UserDTO implements Serializable {
    private double avg;
    private STATUS_STUDENT status;
    private Set<RecordDTO> records ;
}
