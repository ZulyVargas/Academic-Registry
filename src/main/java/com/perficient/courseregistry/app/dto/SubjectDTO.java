package com.perficient.courseregistry.app.dto;

import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class SubjectDTO implements Serializable {
    private UUID subjectId;
    @NotBlank(message = "The title of the subject cannot be empty.")
    private  String title;
    @NotBlank(message = "The code of the subject cannot be empty.")
    private String code;

    @NotBlank(message = "The number of credits of the subject cannot be empty. ")
    private String credits;

    private boolean active;
    private Set<SubjectDTO> prerrequisites;

}