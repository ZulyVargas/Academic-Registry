package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserMapperTest {
    private final IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
    private User userTest;
    private UserDTO userDTOTest;

    @BeforeAll
    public void setUp(){
        userTest = User.builder().userId(UUID.randomUUID())
                                 .name("USER TEST")
                                 .email("usertest@test.edu")
                                 .gender("F")
                                 .username("user.test")
                                 .active(true)
                                 .build();
        userDTOTest = UserDTO.builder().userId(userTest.getUserId())
                                       .name("USER TEST")
                                       .email("usertest@test.edu")
                                       .gender("F")
                                       .username("user.test")
                                       .active(true)
                                       .build();
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
