package com.perficient.courseregistry.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.services.impl.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    private StudentDTO studentDTOTest;
    private StudentController studentController;
    private ObjectMapper objectMapper;
    private File studentJson = new File("src/test/resources/jsons/student.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        studentDTOTest = objectMapper.readValue(studentJson, StudentDTO.class);
        studentController = new StudentController(studentService);
    }

    @Test
    public void getAllStudents_shouldReturnListOfDTOStudent() {
        List<StudentDTO> studentDTOList = new ArrayList<>();
        studentDTOList.add(studentDTOTest);
        when(studentService.getAllStudents(any(), any(), any())).thenReturn(studentDTOList);

        ResponseEntity<List<StudentDTO>> response = studentController.getAllStudents(1,1,true);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOList, response.getBody());
    }

    @Test
    public void getStudentById_givenExistingId_shouldReturnStudentDTO() {
        when(studentService.getStudentById(any(String.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.getStudentById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void getStudentById_givenNonExistingId_shouldThrowException(){
        when(studentService.getStudentById(any(String.class))).thenThrow( new UserException(UserException.USER_ID_EXCEPTION, "ID"));

        assertThrows(UserException.class, () -> studentController.getStudentById("1"));
    }

    @Test
    public void addStudent_givenValidStudentDTO_shouldReturnStudentDTO(){
        when(studentService.addStudent(any(StudentDTO.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.addStudent(studentDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void addStudent_givenInvalidStudentDTO_shouldThrowExceptio(){
        when(studentService.addStudent(any(StudentDTO.class))).thenThrow(new UserException(UserException.USER_INSERT_EXCEPTION, "Student"));
        studentDTOTest.setName("");

        assertThrows(UserException.class, () -> studentController.addStudent(studentDTOTest));
    }

    @Test
    public void updateStudent_givenValidStudentDTO_shouldReturnStudentDTO(){
        when(studentService.updateStudent(any(StudentDTO.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.updateStudent(studentDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void updateStudent_givenInvalidStudentDTO_shouldThrowExceptio(){
        when(studentService.updateStudent(any(StudentDTO.class))).thenThrow(new UserException(UserException.USER_UPDATE_EXCEPTION, "Student"));
        studentDTOTest.setName("");

        assertThrows(UserException.class, () -> studentController.updateStudent(studentDTOTest));
    }

    @Test
    public void deleteStudent_givenExistingId_shouldReturnTrue(){
        when(studentService.deleteStudent(any(String.class))).thenReturn(true);

        ResponseEntity<Boolean> response = studentController.deleteStudent(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

    @Test
    public void deleteStudent_givenNonExistingId_shouldReturnFalse(){
        when(studentService.deleteStudent(any(String.class))).thenReturn(false);

        ResponseEntity<Boolean> response = studentController.deleteStudent(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(false, response.getBody());
    }

}
