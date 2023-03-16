package com.perficient.courseregistry.app.exception;


import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.exception.custom.GeneralException;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.exception.custom.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.ConnectException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ExceptionHandler(value= {SubjectException.class, UserException.class, CourseException.class})
    public ResponseEntity<Error> handleResponseException(GeneralException exception){
        Error error = new Error(exception.getField(), exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value={ConnectException.class})
    public ResponseEntity<Error> handleDatabaseConnectionException(ConnectException exception){
        Error error = new Error("Data Source", "Unable to connect to data source", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value={SQLException.class})
    public ResponseEntity<Error> handleDatabaseException(SQLException  exception){
        Error error = new Error("Data Source", "Unable to get data from source", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
