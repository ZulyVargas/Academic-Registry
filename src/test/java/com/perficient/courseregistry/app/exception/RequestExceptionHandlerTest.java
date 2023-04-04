package com.perficient.courseregistry.app.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.perficient.courseregistry.app.dto.CourseDTO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class RequestExceptionHandlerTest {

    private RequestExceptionHandler requestExceptionHandler = new RequestExceptionHandler();
    private MethodArgumentNotValidException methodArgumentNotValidException;

    private InvalidFormatException invalidFormatException;

    @Before
    public void setUp() throws NoSuchMethodException {
        BeanPropertyBindingResult result = new BeanPropertyBindingResult(null, "Object");
        methodArgumentNotValidException = new MethodArgumentNotValidException(new MethodParameter(CourseDTO.class.getMethod("setCourseId", UUID.class), 0), result);

        invalidFormatException = new InvalidFormatException("ERROR", "Value", null);
    }

    @Test
    public void handleRequestException(){
        List<Error> expectedErrors = methodArgumentNotValidException.getBindingResult().getAllErrors().stream()
                .map((e) -> new Error(((FieldError) e).getField(), e.getDefaultMessage(), HttpStatus.BAD_REQUEST,ZonedDateTime.now(ZoneId.of("America/Bogota"))))
                .collect(Collectors.toList());
        ResponseEntity<List<Error>> responseExpected = new ResponseEntity<>(expectedErrors,HttpStatus.BAD_REQUEST);

        ResponseEntity<List<Error>> responseReturned =  requestExceptionHandler.handleRequestException(methodArgumentNotValidException);

        assertEquals(responseExpected,responseReturned);
    }

    @Test
    public void handleRequestExceptionEnums(){
        ResponseEntity<Error> responseExpected = new ResponseEntity<>(new Error(invalidFormatException.getValue().toString(), "The indicated value is not allowed, you must use the default values or use the correct format",
                                                                                HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota"))),
                                                                        HttpStatus.BAD_REQUEST);
        ResponseEntity<Error> responseReturned = requestExceptionHandler.handleRequestExceptionEnums(invalidFormatException);

        assertEquals(responseExpected,responseReturned);
    }


}
