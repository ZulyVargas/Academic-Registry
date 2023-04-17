package com.perficient.courseregistry.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.entities.Professor;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ProfessorMapperTest {

    private final IProfessorMapper professorMapper = Mappers.getMapper(IProfessorMapper.class);
    private Professor professorTest;
    private ProfessorDTO professorDTOTest;
    private ObjectMapper objectMapper;
    private File professorJson = new File("src/test/resources/jsons/professor.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        professorTest = objectMapper.readValue(professorJson, Professor.class);
        professorDTOTest = objectMapper.readValue(professorJson, ProfessorDTO.class);
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