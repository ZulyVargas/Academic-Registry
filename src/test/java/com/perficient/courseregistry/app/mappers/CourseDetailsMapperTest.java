package com.perficient.courseregistry.app.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CourseDetailsMapperTest {
    private final ICourseDetailsMapper courseDetailsMapper = Mappers.getMapper(ICourseDetailsMapper.class);
    private CourseDTO courseDTOTest;
    private CourseDetails courseDetailsTest;
    private ObjectMapper objectMapper;
    private File courseJson = new File("src/test/resources/jsons/course.json");
    private File courseDetailsJson = new File("src/test/resources/jsons/courseDetails.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        courseDTOTest = objectMapper.readValue(courseJson, CourseDTO.class);
        courseDetailsTest = objectMapper.readValue(courseDetailsJson, CourseDetails.class);
    }

    @Test
    public void courseDetailsToCourseDTO(){
        assertEquals(courseDetailsTest,courseDetailsMapper.courseDTOToCourseDetails(courseDTOTest));
    }

}
