package com.perficient.courseregistry.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {
    private UUID userId;
    @NotNull
    private String name;
    @NotNull
    private String username;
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String gender;
    private boolean active;
}
