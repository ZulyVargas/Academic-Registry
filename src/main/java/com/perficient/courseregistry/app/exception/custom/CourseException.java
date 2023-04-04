package com.perficient.courseregistry.app.exception.custom;

public class CourseException extends GeneralException {
    public static final String COURSE_ID_EXCEPTION = "The course with the given ID does not exist.";
    public static final String COURSE_DELETE_EXCEPTION = "The given course could not be deleted.";
    public static final String COURSE_UNIQUE_EXCEPTION = "The given course could not be saved. The given course already exists.";
    public static final String COURSE_UPDATE_EXCEPTION = "The given course could not be updated.";

    public CourseException(String mesagge, String field) {
        super(mesagge, field);
    }
}
