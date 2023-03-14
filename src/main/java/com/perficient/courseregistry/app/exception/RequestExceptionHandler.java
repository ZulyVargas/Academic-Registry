package com.perficient.courseregistry.app.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value= {MethodArgumentNotValidException.class})
    public ResponseEntity<List<Error>> handleRequestException(MethodArgumentNotValidException exception){
        List<Error> response = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((e) -> {
            Error error = new Error(((FieldError)e).getField(), e.getDefaultMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
            response.add(error);
        });
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={InvalidFormatException.class})
    public ResponseEntity<Error> handleRequestExceptionEnums(InvalidFormatException exception){
        Error response = new Error(exception.getValue().toString(), "The indicated value is not allowed, you must use the default values.", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}