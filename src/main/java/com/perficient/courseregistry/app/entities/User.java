package com.perficient.courseregistry.app.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(value="users")
public class User {

    @Column("user_id")
    private @Id UUID userId;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    @Column("active_user")
    private boolean active;

}