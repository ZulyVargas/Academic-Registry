package com.perficient.courseregistry.app.exception.custom;


import lombok.Getter;

@Getter
public class GeneralException extends RuntimeException {
    String field;
    public GeneralException(String mesagge,String field){
        super(mesagge);
        this.field = field;
    }
}
