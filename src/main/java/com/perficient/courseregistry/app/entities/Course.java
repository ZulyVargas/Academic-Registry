package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Course {
    private String courseId;
    private String group;
    private String quota;
    private Professor professor;
    private Subject subject;

}
