package com.perficient.courseregistry.app.exception.custom;

import lombok.Getter;

@Getter
public class SubjectException extends GeneralException{
    public static final String SUBJECT_TITLE_EXCEPTION = "The subject with the given title does not exist.";
    public static final String SUBJECT_ID_EXCEPTION = "The subject with the given ID does not exist.";
    public static final String SUBJECT_INSERT_EXCEPTION = "The given subject could not be saved.";
    public static final String SUBJECT_UPDATE_EXCEPTION = "The given subject could not be updated.";
    public static final String SUBJECT_DELETE_EXCEPTION = "The given subject could not be deleted.";

    public SubjectException(String message, String field){
        super(message, field);
    }
}