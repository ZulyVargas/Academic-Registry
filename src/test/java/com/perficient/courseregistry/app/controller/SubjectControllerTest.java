package com.perficient.courseregistry.app.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.perficient.courseregistry.app.services.impl.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SubjectControllerTest {
    @Mock
    private SubjectService subjectService;
    private SubjectDTO subjectDTOTest;
    private SubjectController subjectController;

    @Before
    public void setUp() {
        subjectDTOTest = SubjectDTO.builder()
                                   .subjectId(UUID.randomUUID())
                                   .title("SUBJECT TEST")
                                   .code("SUBT")
                                   .credits(4)
                                   .active(true)
                                   .prerrequisites(new HashSet<>())
                                   .build();
        subjectController = new SubjectController(subjectService);
    }

    @Test
    public void getAllSubjects_shouldReturnSetOfDTOSubjects() throws Exception {
        Set<SubjectDTO> subjectDTOSet = new HashSet<>();
        subjectDTOSet.add(subjectDTOTest);
        when(subjectService.getAllSubjects(any(), any(), any())).thenReturn(subjectDTOSet);

        ResponseEntity<Set<SubjectDTO>> response = subjectController.getAllSubjects(1,1,true);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOSet, response.getBody());
    }

    @Test
    public void getSubjectById_givenId_shouldReturnSubjectDTO() throws Exception{
        when(subjectService.getSubjectById(anyString())).thenReturn(subjectDTOTest);

        ResponseEntity<SubjectDTO> response = subjectController.getSubjectById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void getSubjectByTitle_givenTitle_shouldReturnSubjectDTO() throws Exception {
        when(subjectService.getSubjectByTitle(anyString())).thenReturn(subjectDTOTest);

        ResponseEntity<SubjectDTO> response = subjectController.getSubjectByTitle("TEST");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void addSubject_givenSubjectDTO_shouldReturnSubjectDTO() throws Exception {
        when(subjectService.addSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);

        ResponseEntity<SubjectDTO> response = subjectController.addSubject(subjectDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void updateSubject_givenSubjectDTO_shouldReturnSubjectDTO() throws Exception{
        when(subjectService.updateSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);
        subjectDTOTest.setTitle("New title");

        ResponseEntity<SubjectDTO> response = subjectController.updateSubject(subjectDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void deleteSubject_givenId_shouldReturnBoolean() throws Exception {
        when(subjectService.deleteSubject(anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = subjectController.deleteSubject(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

}
