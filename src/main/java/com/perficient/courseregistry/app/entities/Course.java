package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;
import java.sql.Time;

@Getter
@Setter
public class Course {
    private String course_id;
    private String title;
    private String code;
    private String quota;
    private Time startDate;
    private Time finishDate;

}
