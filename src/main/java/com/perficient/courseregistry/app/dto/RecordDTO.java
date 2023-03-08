package com.perficient.courseregistry.app.dto;

import com.perficient.courseregistry.app.enums.GRADE_TYPE;
import lombok.*;

@Data
public class RecordDTO {
    private String recordId;
    private String courseId;
    private GRADE_TYPE type;
    private double grade;
}
