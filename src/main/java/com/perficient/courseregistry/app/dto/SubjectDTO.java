package com.perficient.courseregistry.app.dto;

import lombok.*;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Data
@Builder
public class SubjectDTO implements Serializable {
    @Pattern(regexp = "^[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}$" , message = "ID format error.")
    private String subjectId;
    @NotBlank(message = "The title of the subject cannot be empty.")
    private  String title;
    @NotBlank(message = "The code of the subject cannot be empty.")
    private String code;

    @NotBlank(message = "The number of credits of the subject cannot be empty. ")
    private String credits;
    private Set<SubjectDTO> prerrequisites;

}