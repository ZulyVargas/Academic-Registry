package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.mappers.IUserMapper;
import com.perficient.courseregistry.app.repository.IUserRepository;
import com.perficient.courseregistry.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> users = this.userRepository.findAll().stream().map( user -> {
                UserDTO userDto = IUserMapper.INSTANCE.userToUserDTO(user);
                return userDto;
        }).collect(Collectors.toSet());
        return users;
        }

    @Override
    public Set<UserDTO> getAllUsersPaged(Integer limit, Integer offset) {
        Set<UserDTO> users = this.userRepository.findAllPageable(limit,offset).stream().map( user -> {
            UserDTO userDto = IUserMapper.INSTANCE.userToUserDTO(user);
            return userDto;
        }).collect(Collectors.toSet());
        return users;
    }



    @Override
    public UserDTO getUserByUsername(String username) {
        UserDTO userDTO = IUserMapper.INSTANCE.userToUserDTO(this.userRepository.findByUsername(username));
        return userDTO;
    }

    @Override
    public UserDTO getUserById(String id) {
        UserDTO userDTO = IUserMapper.INSTANCE.userToUserDTO(this.userRepository.findById(UUID.fromString(id)));
        return userDTO;
    }
}
