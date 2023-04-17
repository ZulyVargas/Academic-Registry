package com.perficient.courseregistry.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.entities.Student;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class StudentMapperTest {
    private final IStudentMapper studentMapper = Mappers.getMapper(IStudentMapper.class);
    private Student studentTest;
    private StudentDTO studentDTOTest;
    private ObjectMapper objectMapper;
    private File studentJson = new File("src/test/resources/jsons/student.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        studentTest = objectMapper.readValue(studentJson, Student.class);
        studentDTOTest = objectMapper.readValue(studentJson, StudentDTO.class);
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
