package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.UserDTO;

public interface IUserService {

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    Boolean deleteUser(String id);

    UserDTO findByEmail(String email);
}