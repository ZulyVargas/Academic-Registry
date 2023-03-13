package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IProfessorMapper;
import com.perficient.courseregistry.app.repository.IProfessorRepository;
import com.perficient.courseregistry.app.repository.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {
    private Professor professorTest;
    private ProfessorDTO professorDTOTest;

    @Mock
    private IProfessorRepository professorRepository;
    @Mock
    private IUserRepository userRepository;
    private ProfessorService professorService;

    @Before
    public void setUp(){
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
        professorService = new ProfessorService(professorRepository, Mappers.getMapper(IProfessorMapper.class));
    }

    @Test
    public void getAllProfessors_shouldReturnSetOfDTOProfessors() {
        Set<Professor> professorSet = new HashSet<>();
        professorSet.add(professorTest);
        when(professorRepository.findAll(any(Integer.class), any(Integer.class), any(Boolean.class))).thenReturn(professorSet);

        Set<ProfessorDTO> professorDTOsReturned = professorService.getAllProfessors(1,1, Optional.of(true));

        assertEquals(new HashSet<>(Set.of(professorDTOTest)),professorDTOsReturned);

    }

    @Test
    public void getProfessorById_givenId_shouldReturnProfessorDTO(){
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(professorTest));

        ProfessorDTO professorDTOReturned = professorService.getProfessorById(UUID.randomUUID().toString());

        assertEquals(professorDTOTest, professorDTOReturned);
    }

    @Test
    public void getProfessorById_givenId_shouldThrowException(){
        when(professorRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserException.class, ()->  professorService.getProfessorById(UUID.randomUUID().toString()));
    }

    @Test
    public void getProffesorsByDegree_givenDegree_shouldReturnSetOfDTOProfessors(){
        Set<Professor> professorSet = new HashSet<>();
        professorSet.add(professorTest);
        when(professorRepository.findAllByDegree(any(String.class))).thenReturn(professorSet);

        Set<ProfessorDTO> professorDTOsReturned = professorService.getProfessorsByDegree("TEST");

        assertEquals(new HashSet<>(Set.of(professorDTOTest)), professorDTOsReturned);
    }

}
