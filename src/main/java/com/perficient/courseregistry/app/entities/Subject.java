package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Getter
@Setter
public class Subject {
    private String subject_id;
    private  String title;
    private Professor professor;
    private Time startDate;
}
