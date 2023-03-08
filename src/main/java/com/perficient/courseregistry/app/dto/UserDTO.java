package com.perficient.courseregistry.app.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;
@Data
@SuperBuilder
public class UserDTO implements Serializable {
    private UUID userId;
    @NotBlank(message = "The name of the user cannot be empty")
    private String name;
    @NotBlank(message = "The username cannot be empty.")
    private String username;
    @Size(min=8, message = "The password must have a minimum length of 8 characters.")
    private String password;
    @NotBlank(message = "The email address of the user cannot be empty.")
    @Email(message = "Please provide a valid email address.")
    private String email;
    @NotBlank(message = "The gender of the user cannot be empty")
    private String gender;
    private boolean active;
}