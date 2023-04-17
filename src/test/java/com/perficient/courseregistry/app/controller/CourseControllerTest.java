package com.perficient.courseregistry.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.services.impl.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
    @Mock
    private CourseService courseService;
    private CourseDTO courseDTOTest;
    private CourseController courseController;
    private ObjectMapper objectMapper;
    private File courseJson = new File("src/test/resources/jsons/course.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        courseDTOTest = objectMapper.readValue(courseJson, CourseDTO.class);
        courseController = new CourseController(courseService);
   }

    @Test
    public void getAllCourses_shouldReturnListOfDTOCourses(){
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseDTOList.add(courseDTOTest);
        when(courseService.getAllCourses(any(), any(), any())).thenReturn(courseDTOList);

        ResponseEntity<List<CourseDTO>> response = courseController.getAllCourses(1,1,true);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(courseDTOList, response.getBody());
    }

    @Test
    public void getCourseById_givenExistingId_shouldReturnCourseDTO() {
        when(courseService.getCourseById(any(String.class))).thenReturn(courseDTOTest);

        ResponseEntity<CourseDTO> response = courseController.getCourseById(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(courseDTOTest, response.getBody());
    }

    @Test
    public void getCourseById_givenNonExistingId_shouldThrowException(){
        when(courseService.getCourseById(any(String.class))).thenThrow( new CourseException(CourseException.COURSE_ID_EXCEPTION, "ID"));

        assertThrows(CourseException.class, () -> courseController.getCourseById("1"));
    }

    @Test
    public void addCourse_givenValidCourseDTO_shouldReturnCourseDTO(){
        when(courseService.addCourse(any(CourseDTO.class))).thenReturn(courseDTOTest);

        ResponseEntity<CourseDTO> response = courseController.addCourse(courseDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(courseDTOTest, response.getBody());
    }

    @Test
    public void addCourse_givenDuplicateCourseDTO_shouldThrowException(){
        when(courseService.addCourse(any(CourseDTO.class))).thenReturn(courseDTOTest)
                                                           .thenThrow( new CourseException(CourseException.COURSE_UNIQUE_EXCEPTION, "Course"));
        courseController.addCourse(courseDTOTest);
        assertThrows(CourseException.class, () -> courseController.addCourse(courseDTOTest));
    }

    @Test
    public void updateCourse_givenValidCourseDTO_shouldReturnCourseDTO(){
        when(courseService.updateCourse(any(CourseDTO.class))).thenReturn(courseDTOTest);

        ResponseEntity<CourseDTO> response = courseController.updatedCourse(courseDTOTest);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(courseDTOTest, response.getBody());
    }

    @Test
    public void updateCourse_givenInvalidCourseDTO_shouldThrowException(){
        when(courseService.updateCourse(any(CourseDTO.class))).thenThrow( new CourseException(CourseException.COURSE_UPDATE_EXCEPTION, "Course"));

        assertThrows(CourseException.class, () -> courseController.updatedCourse(courseDTOTest));
    }

    @Test
    public void deleteSubject_givenExistingId_shouldReturnTrue(){
        when(courseService.deleteCourse(anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = courseController.deleteCourse(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(true, response.getBody());
    }

    @Test
    public void deleteSubject_givenNonExistingId_shouldReturnFalse(){
        when(courseService.deleteCourse(anyString())).thenReturn(false);

        ResponseEntity<Boolean> response = courseController.deleteCourse(UUID.randomUUID().toString());

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(false, response.getBody());
    }

}
