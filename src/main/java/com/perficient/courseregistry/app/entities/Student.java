package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student extends User{
    private String student_id;
    private double average;

}
