package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Record;
import com.perficient.courseregistry.app.entities.Student;
import com.perficient.courseregistry.app.entities.User;
import com.perficient.courseregistry.app.enums.GRADE_TYPE;
import com.perficient.courseregistry.app.enums.STATUS_STUDENT;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IStudentMapper;
import com.perficient.courseregistry.app.mappers.IUserMapper;
import com.perficient.courseregistry.app.repository.IStudentRepository;
import com.perficient.courseregistry.app.repository.IUserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    private Student studentTest;
    private StudentDTO studentDTOTest;
    private UserDTO userDTOTest;
    private User userTest;
    @Mock
    private IStudentRepository studentRepository;
    @Mock
    private IUserRepository userRepository;
    private StudentService studentService;

    @Before
    public void setUp(){
        Set<Record> records = new HashSet<>();
        records.add(new Record(UUID.randomUUID(), UUID.randomUUID(), GRADE_TYPE.FIRST, 3.4));
        studentTest = Student.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .avg(3.4)
                .status(STATUS_STUDENT.ACTIVE)
                .records(new HashSet<>(records))
                .password("ABC")
                .build();
        IStudentMapper studentMapper = Mappers.getMapper(IStudentMapper.class);
        IUserMapper userMapper = Mappers.getMapper(IUserMapper.class);
        studentDTOTest = studentMapper.studentToStudentDto(studentTest);
        userDTOTest = studentMapper.studentDTOToUser(studentDTOTest);
        userTest = userMapper.userDtoToUser(userDTOTest);

        studentService = new StudentService(studentRepository, studentMapper);
        studentService.setUserMapper(userMapper);
        studentService.setUserRepository(userRepository);
    }

    @Test
    public void getAllStudents_shouldReturnListOfDTOProfessors() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(studentTest);
        when(studentRepository.findAll(any(Integer.class), any(Integer.class), any(Boolean.class))).thenReturn(studentList);

        List<StudentDTO> studentDTOsReturned = studentService.getAllStudents(1,1, Optional.of(true));

        assertEquals(new ArrayList<>(List.of(studentDTOTest)), studentDTOsReturned);
    }

    @Test
    public void getStudentById_givenExistingId_shouldReturnStudentDTO(){
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(studentTest));

        StudentDTO studentDTOReturned = studentService.getStudentById(UUID.randomUUID().toString());

        assertEquals(studentDTOTest, studentDTOReturned);
    }

    @Test
    public void getProfessorById_givenNonExistingId_shouldThrowException(){
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(UserException.class, ()->  studentService.getStudentById(UUID.randomUUID().toString()));
    }

    @Test
    public void addStudent_givenValidStudentDTO_shouldReturnStudentDTO(){
        when(userRepository.save(any(User.class))).thenReturn(userTest);
        when(studentRepository.save(any(UUID.class),any(Double.class), any(STATUS_STUDENT.class))).thenReturn(true);
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(studentTest));

        StudentDTO studentDTOReturned = studentService.addStudent(studentDTOTest);

        assertEquals(studentDTOTest, studentDTOReturned);
    }

    @Test
    public void addStudent_givenInvalidStudentDTO_shouldThrowException(){
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> studentService.addStudent(studentDTOTest));
    }

    @Test
    public void updateStudent_givenValidStudentDTO_shouldReturnStudentDTO() {
        when(userRepository.save(any(User.class))).thenReturn(userTest);
        when(studentRepository.update(any(UUID.class),any(Double.class), any(STATUS_STUDENT.class))).thenReturn(true);
        when(studentRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(studentTest));

        StudentDTO studentDTOReturned = studentService.updateStudent(studentDTOTest);

        assertEquals(studentDTOTest, studentDTOReturned);
    }

    @Test
    public void updateStudent_givenInvalidStudentDTO_shouldThrowException() {
        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> studentService.addStudent((studentDTOTest)));
    }

    @Test
    public void deleteProfessor_givenExistingId_shouldReturnTrue(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenReturn(true);


        Boolean updateActiveExpected = true;
        Boolean updateActiveReturned = studentService.deleteStudent(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteProfessor_givenNonExistingId_shouldReturnFalse(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenReturn(false);

        Boolean updateActiveExpected = false;
        Boolean updateActiveReturned = studentService.deleteStudent(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteProfessor_givenInvalidId_shouldThrowException(){
        when(userRepository.findById(any(String.class))).thenReturn(Optional.ofNullable(userTest));
        when(userRepository.updateActive(any(UUID.class))).thenThrow(new RuntimeException());

        assertThrows(UserException.class, () -> studentService.deleteStudent(UUID.randomUUID().toString()));
    }

}
