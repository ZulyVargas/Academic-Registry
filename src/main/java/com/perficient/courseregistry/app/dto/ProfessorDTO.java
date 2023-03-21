package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.controller.ProfessorController;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorDTO extends UserDTO implements Serializable {

    @NotBlank(message = "The degree cannot be empty.")
    private String degree;

    public ProfessorDTO generateLinks() {
        setLinks(new ArrayList<>());
        getLinks().add(linkTo(ProfessorController.class).slash(this.getUserId().toString()).withSelfRel());
        getLinks().add(linkTo(methodOn(ProfessorController.class).getProffesorsByDegree(degree)).withRel("getByDegree"));
        return this;
    }
}