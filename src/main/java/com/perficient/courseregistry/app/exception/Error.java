package com.perficient.courseregistry.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Error {
    private String field;
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;

    public Error(String message, HttpStatus httpStatus, ZonedDateTime timestamp){
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }
}