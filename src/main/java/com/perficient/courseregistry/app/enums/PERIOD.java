package com.perficient.courseregistry.app.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public enum PERIOD {
    I("First semester of the year"),
    II("Second half of the year "),
    i("Intersemester");

    private String description;

    private PERIOD(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}

