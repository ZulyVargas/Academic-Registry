package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IUserMapper;
import com.perficient.courseregistry.app.repository.IUserRepository;
import com.perficient.courseregistry.app.services.IUserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Data
@Service
public abstract class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IUserMapper userMapper;

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        try{
            return userMapper.userToUserDTO(userRepository.save(userMapper.userDtoToUser(userDTO)));
        }catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "email or username unique");
        }
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        try {
            //encrypt password
            userDTO.setPassword(BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt()));
            return this.addUser(userDTO);
        }catch (Exception ex){
            throw new UserException(UserException.USER_UPDATE_EXCEPTION, "ID");
        }
    }

    @Override
    public Boolean deleteUser(String userId) {
        try{
            Optional<User> user = userRepository.findById(userId);
            if (user.isEmpty() || !user.get().isActive()) return false;
            return userRepository.updateActive(UUID.fromString(userId));
        }catch (Exception ex){
            throw new UserException(UserException.USER_DELETE_EXCEPTION, "ID");
        }
    }
}
