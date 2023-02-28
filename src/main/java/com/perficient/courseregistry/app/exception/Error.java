package com.perficient.courseregistry.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Error {
    private String field;
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;

}
