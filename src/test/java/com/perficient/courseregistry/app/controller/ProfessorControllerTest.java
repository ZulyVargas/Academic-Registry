package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.services.impl.ProfessorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.junit.Assert.*;
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
    public void getAllProfessors_shouldReturnListOfDTOProfessors() {
        List<ProfessorDTO> professorList = new ArrayList<>();
        professorList.add(professorDTOTest);
        when(professorService.getAllProfessors(any(), any(), any(), any())).thenReturn(professorList);

        ResponseEntity<List<ProfessorDTO>> response = professorController.getAllProfessors(1,1,true, null);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorList, response.getBody());
    }

    @Test
    public void getProfessorById_givenExistingId_shouldReturnProfessorDTO() {
        when(professorService.getProfessorById(any(String.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.getProfessorById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void getProfessorById_givenNonExistingId_shouldThrowException() {
        when(professorService.getProfessorById(any(String.class))).thenThrow( new UserException(UserException.USER_ID_EXCEPTION, "ID"));

        assertThrows(UserException.class, () -> professorController.getProfessorById("1"));
    }

    @Test
    public void getAllProffesorsByDegree_givenDegree_shouldReturnListOfDTOProfessors() {
        List<ProfessorDTO> professorList = new ArrayList<>();
        professorList.add(professorDTOTest);
        when(professorService.getProfessorsByDegree(any(), any(), any(), any())).thenReturn(professorList);

        ResponseEntity<List<ProfessorDTO>> response = professorController.getAllProfessors(1,1,true, null);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorList, response.getBody());
    }

    @Test
    public void addProfessor_givenValidProfessorDTO_shouldReturnProfessorDTO(){
        when(professorService.addProfessor(any(ProfessorDTO.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.addProfessor(professorDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void addProfessor_givenInvalidProfessorDTO_shouldThrowException(){
        when(professorService.addProfessor(any(ProfessorDTO.class))).thenThrow(new UserException(UserException.USER_INSERT_EXCEPTION, "Professor"));
        professorDTOTest.setName("");

        assertThrows(UserException.class, () -> professorController.addProfessor(professorDTOTest));
    }

    @Test
    public void updateProfessor_givenValidProfessorDTO_shouldReturnProfessorDTO(){
        when(professorService.updateProfessor(any(ProfessorDTO.class))).thenReturn(professorDTOTest);

        ResponseEntity<ProfessorDTO> response = professorController.updateProfessor(professorDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(professorDTOTest, response.getBody());
    }

    @Test
    public void updateProfessor_givenInvalidProfessorDTO__shouldThrowException(){
        when(professorService.updateProfessor(any(ProfessorDTO.class))).thenThrow(new UserException(UserException.USER_UPDATE_EXCEPTION, "Professor"));
        professorDTOTest.setName("");

        assertThrows(UserException.class, () -> professorController.updateProfessor(professorDTOTest));
    }

    @Test
    public void deleteProfessor_givenExistingId_shouldReturnTrue(){
        when(professorService.deleteProfessor(any(String.class))).thenReturn(true);

        ResponseEntity<Boolean> response = professorController.deleteProfessor(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

    @Test
    public void deleteProfessor_givenNonExistingId_shouldReturnFalse(){
        when(professorService.deleteProfessor(any(String.class))).thenReturn(false);

        ResponseEntity<Boolean> response = professorController.deleteProfessor(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(false, response.getBody());
    }
}

