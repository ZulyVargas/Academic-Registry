package com.perficient.courseregistry.app.dto;

import lombok.Data;
import lombok.Builder;
import java.io.Serializable;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.perficient.courseregistry.app.controller.SubjectController;
import com.perficient.courseregistry.app.hateoas.Resource;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@Data
public class SubjectDTO extends Resource<SubjectDTO> implements Serializable {
    private UUID subjectId;
    @NotBlank(message = "The title of the subject cannot be empty.")
    private  String title;
    @NotBlank(message = "The code of the subject cannot be empty.")
    private String code;

    @Min(value = 1, message = "The minimum number of credits is 1.")
    @Max(value = 4, message = "The maximum number of credits is 4.")
    private Integer credits;

    private boolean active;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Set<SubjectDTO> prerequisites;

    @Override
    public SubjectDTO generateLinks() {
        if(prerequisites!=null) this.setPrerequisites(prerequisites.stream().peek(SubjectDTO::generateLinks).collect(Collectors.toSet()));
        this.add(linkTo(SubjectController.class).slash(this.getSubjectId().toString()).withSelfRel());
        return this;
    }
}