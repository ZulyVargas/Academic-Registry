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
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<List<Error>> handleRequestException(MethodArgumentNotValidException exception) {
        List<Error> response = exception.getBindingResult().getAllErrors().stream()
                .map((e) -> new Error(((FieldError) e).getField(), e.getDefaultMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota"))))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={InvalidFormatException.class})
    public ResponseEntity<Error> handleRequestExceptionEnums(InvalidFormatException exception){
        Error response = new Error(exception.getValue().toString(), "The indicated value is not allowed, you must use the default values or use the correct format", HttpStatus.BAD_REQUEST, ZonedDateTime.now(ZoneId.of("America/Bogota")));
        return  new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }
}