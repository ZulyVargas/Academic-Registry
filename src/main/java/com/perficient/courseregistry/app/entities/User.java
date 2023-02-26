package com.perficient.courseregistry.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class User {

    @Column("user_id")
    private @Id UUID userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private boolean active;

}