package com.perficient.courseregistry.app.exception.custom;

public class UserException extends GeneralException {
    public static final String USER_DELETE_EXCEPTION = "The user with the given ID could not be deleted." ;
    public static final String USER_UPDATE_EXCEPTION = "The given subject could not be updated.";
    public static final String USER_INSERT_EXCEPTION = "The given user could not be saved.";
    public static final String USER_ID_EXCEPTION = "The user with the given ID does not exist.";
    public static final String USER_USERNAME_EXCEPTION = "The user with the given username does not exist.";

    public UserException(String message, String field){
        super(message, field);
    }

}