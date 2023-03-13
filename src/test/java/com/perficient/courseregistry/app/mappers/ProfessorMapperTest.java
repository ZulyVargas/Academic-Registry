package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.entities.Professor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProfessorMapperTest {

    private final IProfessorMapper professorMapper = Mappers.getMapper(IProfessorMapper.class);
    private Professor professorTest;
    private ProfessorDTO professorDTOTest;

    @BeforeAll
    public void setUp() {
        professorTest = Professor.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .degree("TEST")
                .build();
        professorDTOTest = ProfessorDTO.builder().userId(professorTest.getUserId())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .degree("TEST")
                .build();

    }

    @Test
    public void professorToProfessorDTO(){
        assertEquals(professorDTOTest, professorMapper.professorToProfessorDto(professorTest));
    }

    @Test
    public void professorDTOToProfessor(){
        assertEquals(professorTest, professorMapper.professorDtoToProfessor(professorDTOTest));
    }

}