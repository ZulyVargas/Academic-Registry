package com.perficient.courseregistry.app.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends UserDTO implements Serializable {

    @NotBlank(message = "The degree cannot be empty.")
    private String degree;
}