package com.perficient.courseregistry.app.entities;

import java.util.UUID;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import com.perficient.courseregistry.app.enums.ROLE;

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
    private ROLE role;
}