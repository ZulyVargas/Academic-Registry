package com.perficient.courseregistry.app.exception;

import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.exception.custom.GeneralException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.net.ConnectException;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertEquals;

public class ResponseExceptionHandlerTest {

    private ResponseExceptionHandler responseExceptionHandler = new ResponseExceptionHandler();
    private GeneralException exception;
    private ConnectException exceptionConnection;

    private SQLException dbException;

    @Before
    public void setUp(){
        exception = new CourseException(CourseException.COURSE_ID_EXCEPTION, "ID");
        exceptionConnection = new ConnectException();
        dbException = new SQLException();
    }

    @Test
    public void handleResponseException(){
        ResponseEntity<Error> responseExpected = new ResponseEntity<>(new Error(exception.getField(), exception.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota"))), HttpStatus.NOT_FOUND);

        ResponseEntity<Error> responseReturned = responseExceptionHandler.handleResponseException(exception);

        assertEquals(responseExpected, responseReturned);
    }

    @Test
    public void handleDatabaseConnectionException(){
        ResponseEntity<Error> responseExpected = new ResponseEntity<>(new Error("Data Source", "Unable to connect to data source", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("America/Bogota"))), HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Error> responseReturned = responseExceptionHandler.handleDatabaseConnectionException(exceptionConnection);

        assertEquals(responseExpected, responseReturned);
    }

    @Test
    public void handleDatabaseException(){
        ResponseEntity<Error> responseExpected = new ResponseEntity<>(new Error("Data Source", "Unable to get data from source", HttpStatus.INTERNAL_SERVER_ERROR, ZonedDateTime.now(ZoneId.of("America/Bogota"))),HttpStatus.INTERNAL_SERVER_ERROR);

        ResponseEntity<Error> responseReturned = responseExceptionHandler.handleDatabaseException(dbException);

        assertEquals(responseExpected, responseReturned);
    }

}
