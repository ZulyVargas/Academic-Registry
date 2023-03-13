package com.perficient.courseregistry.app.exception.custom;

public class CourseException extends GeneralException {
    public static final String COURSE_INSERT_EXCEPTION = "The given course could not be saved.";
    public static final String COURSE_ID_EXCEPTION = "The course with the given ID does not exist.";
    public CourseException(String mesagge, String field) {
        super(mesagge, field);
    }
}
