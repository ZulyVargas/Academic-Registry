package com.perficient.courseregistry.app.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import com.perficient.courseregistry.app.enums.GRADE_TYPE;

@Data
@AllArgsConstructor
public class RecordDTO {
    private String recordId;
    private String courseId;
    private GRADE_TYPE type;
    private double grade;
}