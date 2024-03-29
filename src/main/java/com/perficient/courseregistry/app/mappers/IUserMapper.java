package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface IUserMapper {

    UserDTO userToUserDTO(User user);
    User userDtoToUser(UserDTO subjectDTO);
}