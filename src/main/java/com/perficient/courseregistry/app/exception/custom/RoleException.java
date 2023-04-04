package com.perficient.courseregistry.app.exception.custom;

public class RoleException extends GeneralException{

    public static final String ROLE_EXCEPTION = "You do not own the necessary permissions and/or role to access the resource.";
    public RoleException() {
        super(ROLE_EXCEPTION, "ROLE");
    }
}
