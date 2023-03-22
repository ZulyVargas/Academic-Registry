package com.perficient.courseregistry.app.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import com.perficient.courseregistry.app.controller.StudentController;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends UserDTO implements Serializable {
    private double avg;
    private STATUS_STUDENT status;
    private Set<RecordDTO> records ;

    public StudentDTO generateLinks() {
        setLinks(new ArrayList<>());
        getLinks().add(linkTo(StudentController.class).slash(this.getUserId().toString()).withSelfRel());
        return this;
    }
}