package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Student extends User{
    private String studentId;
    private double avg;
    private List<Record> records;


}
