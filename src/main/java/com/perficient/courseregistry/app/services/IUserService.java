package com.perficient.courseregistry.app.services;


import com.perficient.courseregistry.app.dto.UserDTO;
import java.util.Optional;
import java.util.Set;

public interface IUserService {

    Set<UserDTO> getAllUsers(Integer limit, Integer offset, Optional<Boolean> isActive);

    UserDTO getUserByUsername(String username);

    UserDTO getUserById(String id);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(String id);
}