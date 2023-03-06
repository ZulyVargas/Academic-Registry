package com.perficient.courseregistry.app.exception;

import com.perficient.courseregistry.app.exception.custom.SubjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value= {SubjectException.class})
    public ResponseEntity<Error> handleResponseException(SubjectException exception){
        Error error = new Error(exception.getField(), exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={ConnectException.class})
    public ResponseEntity<Error> handleDatabaseException(ConnectException exception){
        Error error = new Error("Data Surce", "Unable to connect to data source", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);}


}
