package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

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

}
