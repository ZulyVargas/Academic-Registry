package com.perficient.courseregistry.app.exception.custom;

import lombok.Getter;
@Getter
public class UserException extends RuntimeException {
    private String field;
    public static final String USER_ID_EXCEPTION = "The user with the given ID does not exist.";
    public static final String USER_USERNAME_EXCEPTION = "The user with the given username does not exist.";

    public UserException(String message, String field){
        super(message);
        this.field = field;
    }

}