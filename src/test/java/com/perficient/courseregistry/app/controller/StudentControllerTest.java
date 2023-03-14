package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import com.perficient.courseregistry.app.services.impl.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentControllerTest {
    @Mock
    private StudentService studentService;
    private StudentDTO studentDTOTest;
    private StudentController studentController;

    @Before
    public void setUp(){
        studentDTOTest = StudentDTO.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .avg(3.4)
                .status(STATUS_STUDENT.ACTIVE)
                .build();
        studentController = new StudentController(studentService);
    }

    @Test
    public void getAllStudents_shouldReturnSetOfDTOStudent() {
        Set<StudentDTO> studentDTOSet = new HashSet<>();
        studentDTOSet.add(studentDTOTest);
        when(studentService.getAllStudents(any(), any(), any())).thenReturn(studentDTOSet);

        ResponseEntity<Set<StudentDTO>> response = studentController.getAllStudents(1,1,true);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOSet, response.getBody());
    }

    @Test
    public void getStudentById_givenId_shouldReturnStudentDTO() {
        when(studentService.getStudentById(any(String.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.getStudentById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void addStudent_givenStudentDTO_shouldReturnStudentDTO(){
        when(studentService.addStudent(any(StudentDTO.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.addStudent(studentDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void updateStudent_givenStudentDTO_shouldReturnStudentDTO(){
        when(studentService.updateStudent(any(StudentDTO.class))).thenReturn(studentDTOTest);

        ResponseEntity<StudentDTO> response = studentController.updateStudent(studentDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(studentDTOTest, response.getBody());
    }

    @Test
    public void deleteProfessor_givenId_shouldReturnBoolean(){
        when(studentService.deleteStudent(any(String.class))).thenReturn(true);

        ResponseEntity<Boolean> response = studentController.deleteStudent(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

}
