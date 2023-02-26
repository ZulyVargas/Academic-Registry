package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserMapper {


    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
    UserDTO userToUserDTO(User user);

    User userDtoToUser(UserDTO subjectDTO);
}