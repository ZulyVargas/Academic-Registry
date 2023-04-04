package com.perficient.courseregistry.app.dto.auth;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String email;
    private String password;
}
