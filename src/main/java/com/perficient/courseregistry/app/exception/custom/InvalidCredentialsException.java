package com.perficient.courseregistry.app.exception.custom;

import com.perficient.courseregistry.app.enums.SecurityError;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import com.perficient.courseregistry.app.exception.Error;
import javax.ws.rs.InternalServerErrorException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Getter
public class InvalidCredentialsException extends GeneralException  {
    private final Error serverErrorResponseDto;
    public static final String LOGIN_EXCEPTION = "The user with the given credentials does not exist.";
    public InvalidCredentialsException()   {
        super(LOGIN_EXCEPTION, "email-password");
        this.serverErrorResponseDto = new Error(LOGIN_EXCEPTION, SecurityError.INVALID_USER_CREDENTIALS.toString(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("America/Bogota")));
    }
}
