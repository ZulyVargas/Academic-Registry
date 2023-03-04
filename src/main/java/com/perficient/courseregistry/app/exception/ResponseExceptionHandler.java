package com.perficient.courseregistry.app.exception;


import com.perficient.courseregistry.app.exception.custom.GeneralException;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.exception.custom.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value= {SubjectException.class, UserException.class})
    public ResponseEntity<Error> handleResponseException(GeneralException exception){
        Error error = new Error(exception.getField(), exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
