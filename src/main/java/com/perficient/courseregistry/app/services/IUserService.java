package com.perficient.courseregistry.app.services;


import com.perficient.courseregistry.app.dto.UserDTO;
import org.springframework.http.HttpStatus;

import java.util.Set;

public interface IUserService {
    Set<UserDTO> getAllUsers();

    Set<UserDTO> getAllUsersPaged(Integer limit, Integer offset);

    UserDTO getUserByUsername(String username);

    UserDTO getUserById(String id);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(String id);
}