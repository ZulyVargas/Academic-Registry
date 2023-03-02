package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.entities.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SubjectDTO implements Serializable {
    private UUID subjectId;
    @NotBlank(message = "The title of the subject cannot be empty.")
    private  String title;
    @NotBlank(message = "The code of the subject cannot be empty.")
    private String code;

    @NotBlank(message = "The number of credits of the subject cannot be empty. ")
    private String credits;
    private Set<SubjectDTO> prerrequisites;

}