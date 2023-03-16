package com.perficient.courseregistry.app.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import com.perficient.courseregistry.app.mappers.ISubjectMapper;
import com.perficient.courseregistry.app.repository.ISubjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {
    private Subject subjectTest;
    private SubjectDTO subjectDTOTest;
    @Mock
    private ISubjectRepository subjectRepository;
    private SubjectService subjectService;

    @Before
    public void setUp() {
        subjectTest = new Subject();
        subjectTest.setSubjectId(UUID.randomUUID());
        subjectTest.setTitle("SUBJECT TEST");
        subjectTest.setCode("SUBT");
        subjectTest.setCredits(4);
        subjectTest.setActive(true);
        subjectTest.setPrerrequisites(new HashSet<>());
        subjectDTOTest = Mappers.getMapper(ISubjectMapper.class).subjectToSubjectDTO(subjectTest);
        subjectService = new SubjectService(subjectRepository, Mappers.getMapper(ISubjectMapper.class));
    }

    @Test
    public void getAllSubjects_shouldReturnSetOfDTOSubjects() {
        Set<Subject> subjectSet = new HashSet<>();
        subjectSet.add(subjectTest);
        when(subjectRepository.findAll(any(Integer.class), any(Integer.class), any(Boolean.class))).thenReturn(subjectSet);

        Set<SubjectDTO> subjectDTOsReturned = subjectService.getAllSubjects(10, 10, Optional.of(true));

        assertEquals(new HashSet<>(Set.of(subjectDTOTest)), subjectDTOsReturned);
    }

    @Test
    public void getSubjectByTitle_givenValidTitle_shouldReturnSubjectDTO() {
        when(subjectRepository.findByTitle(any(String.class))).thenReturn(subjectTest);

        SubjectDTO subjectDTOReturned = subjectService.getSubjectByTitle("SUBT");

        assertEquals(subjectDTOTest, subjectDTOReturned);
    }

    @Test
    public void getSubjectByTitle_givenEmptyTitle_shouldThrowException() {
        when(subjectRepository.findByTitle(any())).thenReturn(null);

        assertThrows(SubjectException.class, () -> subjectService.getSubjectByTitle(""));

    }

    @Test
    public void getSubjectById_givenValidId_shouldReturnSubjectDTO() {
        when(subjectRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(subjectTest));

        SubjectDTO subjectDTOReturned = subjectService.getSubjectById(UUID.randomUUID().toString());

        assertEquals(subjectDTOTest, subjectDTOReturned);
    }

    @Test
    public void getSubjectById_givenInvalidId_shouldThrowException() {
        when(subjectRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(SubjectException.class, () -> subjectService.getSubjectById(UUID.randomUUID().toString()));
    }

    @Test
    public void addSubject_givenValidSubjectDTO_shouldReturnSubjectDTO(){
        when(subjectRepository.save(any(Subject.class))).thenReturn(subjectTest);

        SubjectDTO subjectDTOReturned = subjectService.addSubject(subjectDTOTest);

        assertEquals(subjectDTOTest, subjectDTOReturned);

    }

    @Test
    public void addSubject_givenInvalidSubjectDTO_shouldThrowException(){
        when(subjectRepository.save(any(Subject.class))).thenThrow(new RuntimeException());

        assertThrows(SubjectException.class, () -> subjectService.addSubject(subjectDTOTest));
    }

    @Test
    public void updateSubject_givenValidSubjectDTO_shouldReturnSubjectDTO(){
        when(subjectRepository.save(any(Subject.class))).thenReturn(subjectTest);

        SubjectDTO subjectDTOReturned = subjectService.updateSubject(subjectDTOTest);

        assertEquals(subjectDTOTest, subjectDTOReturned);

    }

    @Test
    public void updateSubject_givenInvalidSubject_shouldThrowException(){
        when(subjectRepository.save(any(Subject.class))).thenThrow(new RuntimeException());

        assertThrows(SubjectException.class, () -> subjectService.updateSubject(subjectDTOTest));
    }

    @Test
    public void deleteSubject_givenExistingId_shouldReturnTrue(){
        when(subjectRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(subjectTest));
        when(subjectRepository.updateActive(any(UUID.class))).thenReturn(true);

        Boolean updateActiveExpected = true;
        Boolean updateActiveReturned = subjectService.deleteSubject(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);

    }

    @Test
    public void deleteSubject_givenNonexistentId_shouldReturnFalse(){
        when(subjectRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(subjectTest));
        when(subjectRepository.updateActive(any(UUID.class))).thenReturn(false);

        Boolean updateActiveExpected = false;
        Boolean updateActiveReturned = subjectService.deleteSubject(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteSubject_givenInvalidId_shouldThrowException(){
        when(subjectRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(subjectTest));
        when(subjectRepository.updateActive(any(UUID.class))).thenThrow(new RuntimeException());

        assertThrows(SubjectException.class, () -> subjectService.deleteSubject(UUID.randomUUID().toString()));
    }
}

