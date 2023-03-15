package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.services.impl.ProfessorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProfessorControllerTest {
    @Mock
    private ProfessorService professorService;
    private ProfessorDTO professorDTOTest;
    private ProfessorController professorController;

    @Before
    public void setUp(){
        professorDTOTest = ProfessorDTO.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .degree("TEST")
                .build();
        professorController = new ProfessorController(professorService);
    }

    @Test
    public void getAllProfessors_shouldReturnSetOfDTOProfessors() {
        List<ProfessorDTO> professorList = new ArrayList<>();
        professorList.add(professorDTOTest);
        when(professorService.getAllProfessors(any(), any(), any())).thenReturn(professorList);

        ResponseEntity<List<ProfessorDTO>> response = professorController.getAllProfessors(1,1,true);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorList, response.getBody());

    }

    @Test
    public void getProfessorById_givenId_shouldReturnProfessorDTO() {
        when(professorService.getProfessorById(any(String.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.getProfessorById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void getProffesorsByDegree_givenDegree_shouldReturnSetOfDTOProfessors() {
        List<ProfessorDTO> professorList = new ArrayList<>();
        professorList.add(professorDTOTest);
        when(professorService.getProfessorsByDegree(any(String.class))).thenReturn(professorList);

        ResponseEntity<List<ProfessorDTO>> response = professorController.getProffesorsByDegree("TEST");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorList, response.getBody());
    }

    @Test
    public void addProfessor_givenProfessorDTO_shouldReturnProfessorDTO(){
        when(professorService.addProfessor(any(ProfessorDTO.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.addProfessor(professorDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void updateProfessor_givenProfessorDTO_shouldReturnProfessorDTO(){
        when(professorService.updateProfessor(any(ProfessorDTO.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.updateProfessor(professorDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void deleteProfessor_givenId_shouldReturnBoolean(){
        when(professorService.deleteProfessor(any(String.class))).thenReturn(true);

        ResponseEntity<Boolean> response = professorController.deleteProfessor(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

}

