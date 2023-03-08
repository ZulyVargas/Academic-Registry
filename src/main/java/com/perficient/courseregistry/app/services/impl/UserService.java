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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IUserMapper userMapper;

    public UserService(IUserRepository userRepository, IUserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Set<UserDTO> getAllUsers(Integer limit, Integer offset,Optional<Boolean> isActive ) {
        return userRepository.findAll(limit,offset, isActive.orElse(true))
                                                .stream()
                                                .map( user -> userMapper.userToUserDTO(user))
                                                .collect(Collectors.toSet());
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

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        try {
            return this.addUser(userDTO);
        }catch (Exception ex){
            throw new UserException(UserException.USER_UPDATE_EXCEPTION, "ID");
        }
    }

    @Override
    public Boolean deleteUser(String userId) {
        try{
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty() || !user.get().isActive()) {
               return false;
            }
            return this.userRepository.updateActive(UUID.fromString(userId));
        }catch (Exception ex){
            throw new UserException(UserException.USER_DELETE_EXCEPTION, "ID");
        }
    }

}
