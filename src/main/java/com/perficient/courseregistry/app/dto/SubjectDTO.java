package com.perficient.courseregistry.app.dto;

import lombok.*;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
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

    @Min(value = 1, message = "The minimum number of credits is 1.")
    @Max(value = 4, message = "The maximum number of credits is 4.")
    private Integer credits;

    private boolean active;
    private Set<SubjectDTO> prerrequisites;

}