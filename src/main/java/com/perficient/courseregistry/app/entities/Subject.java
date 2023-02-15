package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Subject {
    private String subjectId;
    private  String title;
    private String code;

    private List<Subject> prerrequisites;


}
