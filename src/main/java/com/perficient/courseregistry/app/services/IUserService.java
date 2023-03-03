package com.perficient.courseregistry.app.services;


import com.perficient.courseregistry.app.dto.UserDTO;

import java.util.Set;

public interface IUserService {
    Set<UserDTO> getAllUsers();

    Set<UserDTO> getAllUsersPaged(Integer limit, Integer offset);

    UserDTO getUserByUsername(String username);

    UserDTO getUserById(String id);

}