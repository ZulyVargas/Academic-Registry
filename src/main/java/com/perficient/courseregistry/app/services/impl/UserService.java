package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IUserMapper;
import com.perficient.courseregistry.app.repository.IUserRepository;
import com.perficient.courseregistry.app.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository userRepository;


    @Autowired
    private final IUserMapper userMapper;

    public UserService(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        Set<UserDTO> users = StreamSupport.stream(this.userRepository.findAll().spliterator(), false)
                                          .map(user -> userMapper.userToUserDTO(user))
                                          .collect(Collectors.toSet());
        return users;
    }

    @Override
    public Set<UserDTO> getAllUsersPaged(Integer limit, Integer offset) {
        Set<UserDTO> users = this.userRepository.findAllPageable(limit,offset)
                                                .stream()
                                                .map( user -> userMapper.userToUserDTO(user))
                                                .collect(Collectors.toSet());
        return users;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
            Optional<User> user = this.userRepository.findByUsername(username);
            if (user.isPresent()) return userMapper.userToUserDTO(user.get());
            else{
                throw new UserException(UserException.USER_USERNAME_EXCEPTION, "username");
            }
    }

    @Override
    public UserDTO getUserById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        if (user.isPresent()) return userMapper.userToUserDTO(user.get());
        else {
            throw new UserException(UserException.USER_ID_EXCEPTION, "username");
        }
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        try{
            return userMapper.userToUserDTO(this.userRepository.save(userMapper.userDtoToUser(userDTO)));
        }catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "email or username unique");
        }

    }

}
