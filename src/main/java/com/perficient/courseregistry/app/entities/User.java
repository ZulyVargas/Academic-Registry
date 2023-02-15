package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    private String userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private boolean active;

}
