package com.perficient.courseregistry.app.controller;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.exception.custom.SubjectException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.perficient.courseregistry.app.services.impl.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
                                   .prerequisites(new HashSet<>())
                                   .build();
        subjectController = new SubjectController(subjectService);
    }

    @Test
    public void getAllSubjects_shouldReturnSetOfDTOSubjects(){
        List<SubjectDTO> subjectDTOList = new ArrayList<>();
        subjectDTOList.add(subjectDTOTest);
        when(subjectService.getAllSubjects(any(), any(), any(), any())).thenReturn(subjectDTOList);

        ResponseEntity<List<SubjectDTO>> response = subjectController.getAllSubjects(1,1,true, null);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOList, response.getBody());
    }

    @Test
    public void getSubjectById_givenValidId_shouldReturnSubjectDTO(){
        when(subjectService.getSubjectById(anyString())).thenReturn(subjectDTOTest);

        ResponseEntity<SubjectDTO> response = subjectController.getSubjectById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }
    @Test
    public void getSubjectById_givenInvalidId_shouldThrowException(){
        when(subjectService.getSubjectById(anyString())).thenThrow(new SubjectException(SubjectException.SUBJECT_ID_EXCEPTION, "ID"));

        assertThrows(SubjectException.class, () -> subjectController.getSubjectById(UUID.randomUUID().toString()));
    }

    @Test
    public void getSubjectsByTitle_givenTitle_shouldReturnSubjectDTO(){
        List<SubjectDTO> subjectDTOList = new ArrayList<>();
        subjectDTOList.add(subjectDTOTest);
        System.out.println("service " + subjectService);
        when(subjectService.getAllSubjects(any(), any(), any(), any())).thenReturn(subjectDTOList);

        ResponseEntity<List<SubjectDTO>> response = subjectController.getAllSubjects(1,1,true,"TEST");

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOList, response.getBody());
    }

    @Test
    public void addSubject_givenValidSubjectDTO_shouldReturnSubjectDTO(){
        when(subjectService.addSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);

        ResponseEntity<SubjectDTO> response = subjectController.addSubject(subjectDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void addSubject_givenInvalidSubjectDTO_shouldThrowException(){
        when(subjectService.addSubject(any(SubjectDTO.class))).thenThrow(new SubjectException(SubjectException.SUBJECT_INSERT_EXCEPTION, "Subject"));
        subjectDTOTest.setCredits(1234);

        assertThrows(SubjectException.class, () -> subjectController.addSubject(subjectDTOTest));
    }

    @Test
    public void updateSubject_givenValidSubjectDTO_shouldReturnSubjectDTO(){
        when(subjectService.updateSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);
        subjectDTOTest.setTitle("New title");

        ResponseEntity<SubjectDTO> response = subjectController.updateSubject(subjectDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(subjectDTOTest, response.getBody());
    }

    @Test
    public void updateSubject_givenInvalidSubjectDTO_shouldThrowException(){
        when(subjectService.updateSubject(any(SubjectDTO.class))).thenThrow(new SubjectException(SubjectException.SUBJECT_UPDATE_EXCEPTION, "Title"));
        subjectDTOTest.setTitle("");

        assertThrows(SubjectException.class, () -> subjectController.updateSubject(subjectDTOTest));
    }

    @Test
    public void deleteSubject_givenExistingId_shouldReturnTrue(){
        when(subjectService.deleteSubject(anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = subjectController.deleteSubject(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

    @Test
    public void deleteSubject_givenNonexistentId_shouldReturnFalse(){
        when(subjectService.deleteSubject(anyString())).thenReturn(false);

        ResponseEntity<Boolean> response = subjectController.deleteSubject(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(false, response.getBody());
    }

}
