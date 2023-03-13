package com.perficient.courseregistry.app.mappers;

import java.util.UUID;
import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.entities.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentMapperTest {
    private final IStudentMapper studentMapper = Mappers.getMapper(IStudentMapper.class);
    private Student studentTest;
    private StudentDTO studentDTOTest;

    @BeforeAll
    public void setUp(){
        studentTest = Student.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .avg(3.4)
                .build();
        studentDTOTest = StudentDTO.builder().userId(studentTest.getUserId())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .avg(3.4)
                .build();
    }

    @Test
    public void studentToStudentDto(){
        assertEquals(studentDTOTest, studentMapper.studentToStudentDto(studentTest));
    }

    @Test
    public void studentDTOToStudent(){
        assertEquals(studentTest, studentMapper.studentDtoToStudent(studentDTOTest));
    }

}
