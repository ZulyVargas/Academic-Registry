package com.perficient.courseregistry.app.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.User;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IProfessorMapper;
import com.perficient.courseregistry.app.mappers.IUserMapper;
import com.perficient.courseregistry.app.repository.IProfessorRepository;
import com.perficient.courseregistry.app.repository.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {
    private Professor professorTest;
    private ProfessorDTO professorDTOTest;
    private UserDTO userDTOTest;
    private User userTest;
    @Mock
    private IProfessorRepository professorRepository;
    @Mock
    private IUserRepository userRepository;
    private ProfessorService professorService;
    private ObjectMapper objectMapper;
    private File professorJson = new File("src/test/resources/jsons/professor.json");


    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        professorTest = objectMapper.readValue(professorJson, Professor.class);
        professorDTOTest = objectMapper.readValue(professorJson, ProfessorDTO.class);
        IProfessorMapper professorMapper = Mappers.getMapper(IProfessorMapper.class);
        IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
        userDTOTest = professorMapper.professorDTOToUserDTO(professorDTOTest);
        userTest = userMapper.userDtoToUser(userDTOTest);
        professorService = new ProfessorService(professorRepository, professorMapper);
        professorService.setUserMapper(userMapper);
        professorService.setUserRepository(userRepository);
    }

    @Test
    public void getAllProfessors_shouldReturnListOfDTOProfessors() {
        List<Professor> professorList= new ArrayList<>();
        professorList.add(professorTest);
        when(professorRepository.findAll(any(Integer.class), any(Integer.class), any(Boolean.class))).thenReturn(professorList);

        List<ProfessorDTO> professorDTOsReturned = professorService.getAllProfessors(1,1, Optional.of(true), Optional.empty());

        assertEquals(new ArrayList<>(List.of(professorDTOTest)),professorDTOsReturned);
    }

    @Test
    public void getProfessorById_givenExistingId_shouldReturnProfessorDTO(){
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(professorTest));

        ProfessorDTO professorDTOReturned = professorService.getProfessorById(UUID.randomUUID().toString());

        assertEquals(professorDTOTest, professorDTOReturned);
    }

    @Test
    public void getProfessorById_givenNonExistingId_shouldThrowException(){
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserException.class, ()->  professorService.getProfessorById(UUID.randomUUID().toString()));
    }

    @Test
    public void getProffesorsByDegree_givenDegree_shouldReturnListOfDTOProfessors(){
        List<Professor> professorList = new ArrayList<>();
        professorList.add(professorTest);
        when(professorRepository.findAllByDegree(any(Integer.class), any(Integer.class), any(Boolean.class), any(String.class))).thenReturn(professorList);

        List<ProfessorDTO> professorDTOsReturned = professorService.getProfessorsByDegree(1,1,true, "TEST");

        assertEquals(new ArrayList<>(List.of(professorDTOTest)), professorDTOsReturned);
    }

    @Test
    public void addProfessor_givenValidProfessorDTO_shouldReturnProfessorDTO(){
        when(userRepository.save(any(User.class))).thenReturn(userTest);
        when(professorRepository.save(any(UUID.class),any(String.class))).thenReturn(true);
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(professorTest));

        ProfessorDTO professorDTOsReturned = professorService.addProfessor(professorDTOTest);

        assertEquals(professorDTOTest,professorDTOsReturned);
    }

    @Test
    public void addProfessor_givenInvalidProfessorDTO_shouldThrowException(){
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> professorService.addProfessor(professorDTOTest));
    }
    @Test
    public void updateProfessor_givenValidProfessorDTO_shouldReturnProfessorDTO(){
        when(userRepository.save(any(User.class))).thenReturn(userTest);
        when(professorRepository.update(any(UUID.class),any(String.class))).thenReturn(true);
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(professorTest));

        ProfessorDTO professorDTOsReturned = professorService.updateProfessor(professorDTOTest);

        assertEquals(professorDTOTest,professorDTOsReturned);
    }

    @Test
    public void updateProfessor_givenInvalidProfessorDTO_shouldThrowException(){
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> professorService.addProfessor(professorDTOTest));
    }

    @Test
    public void deleteProfessor_givenExistingId_shouldReturnTrue(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenReturn(true);


        Boolean updateActiveExpected = true;
        Boolean updateActiveReturned = professorService.deleteProfessor(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }
    @Test
    public void deleteProfessor_givenNonExistingId_shouldReturnFalse(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenReturn(false);

        Boolean updateActiveExpected = false;
        Boolean updateActiveReturned = professorService.deleteProfessor(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteProfessor_givenInvalidId_shouldThrowException(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> professorService.deleteProfessor(UUID.randomUUID().toString()));
    }
}
