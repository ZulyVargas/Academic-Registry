package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Professor extends User{
    private String professor_id;
    private String degree;

}
