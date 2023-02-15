package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Record {


    private String recordId;
    private Course course;
    private double grade;


}
