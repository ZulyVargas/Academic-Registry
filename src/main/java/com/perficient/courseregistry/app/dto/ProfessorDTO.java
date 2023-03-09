package com.perficient.courseregistry.app.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends UserDTO implements Serializable {
    private String degree;
}