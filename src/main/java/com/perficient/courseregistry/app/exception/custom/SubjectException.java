package com.perficient.courseregistry.app.exception.custom;

import lombok.Getter;

@Getter
public class SubjectException extends RuntimeException{
    private final String field;
    public static final String SUBJECT_TITLE_EXCEPTION = "The subject with the given title does not exist.";
    public SubjectException(String message, String field){
        super(message);
        this.field = field;
    }
}
