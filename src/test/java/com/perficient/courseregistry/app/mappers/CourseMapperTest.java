package com.perficient.courseregistry.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CourseMapperTest {
    private final ICourseMapper courseMapperTest = Mappers.getMapper(ICourseMapper.class);
    private Course courseTest;
    private CourseDTO courseDTOTest;
    private ObjectMapper objectMapper;
    private File courseJson = new File("src/test/resources/jsons/course.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        courseTest = objectMapper.readValue(courseJson, Course.class);
        courseDTOTest = objectMapper.readValue(courseJson, CourseDTO.class);
    }

    @Test
    public void courseToCourseDTO(){
        assertEquals(courseDTOTest, courseMapperTest.courseToCourseDTO(courseTest));
    }

    @Test
    public void courseDTOToCourse(){
        assertEquals(courseTest, courseMapperTest.courseDTOToCourse(courseDTOTest));
    }
}
