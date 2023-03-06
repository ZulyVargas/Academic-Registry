package com.perficient.courseregistry.app.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Error {
    private String field;
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;

}