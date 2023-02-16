package com.perficient.courseregistry.app.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Subject {
    private String subjectId;
    private  String title;
    private String code;
    private Set<Subject> prerequisites;


}
