package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.controller.CourseController;
import com.perficient.courseregistry.app.controller.SubjectController;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import com.perficient.courseregistry.app.hateoas.Resource;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Data
public class CourseDTO extends Resource<CourseDTO> implements Serializable {

    private UUID courseId;
    private String groupNumber;
    @NotNull
    @Positive
    private Integer quota;
    private ProfessorDTO professor;
    @NotNull
    private SubjectDTO subject;
    private STATUS_COURSE statusCourse;
    @NotNull
    private String year;
    @NotNull
    private PERIOD period;
    private boolean active;

    @Override
    public CourseDTO generateLinks() {
        setSubject(subject.generateLinks());
        this.add(linkTo(CourseController.class).slash(this.getCourseId().toString()).withSelfRel());
        return this;
    }
}
