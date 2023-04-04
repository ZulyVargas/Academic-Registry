package com.perficient.courseregistry.app.dto.auth;

import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;


@Data
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private Date expirationDate;
}
