package com.perficient.courseregistry.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class UserMapperTest {
    private final IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
    private User userTest;
    private UserDTO userDTOTest;
    private ObjectMapper objectMapper;
    private File userJson = new File("src/test/resources/jsons/user.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        userTest = objectMapper.readValue(userJson, User.class);
        userDTOTest = objectMapper.readValue(userJson, UserDTO.class);
    }

    @Test
    public void userToUserDTO(){
        assertEquals(userDTOTest, userMapper.userToUserDTO(userTest));
    }

    @Test
    public void setUserDTOToUser(){
        assertEquals(userTest, userMapper.userDtoToUser(userDTOTest));
    }

}
