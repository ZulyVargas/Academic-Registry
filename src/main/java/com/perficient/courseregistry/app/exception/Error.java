package com.perficient.courseregistry.app.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class Error {
    private final String field;
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

}