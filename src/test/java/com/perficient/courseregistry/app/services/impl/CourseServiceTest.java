package com.perficient.courseregistry.app.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.utils.adapters.ICourseAdapterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {
    private Course courseTest;
    private CourseDTO courseDTOTest;
    @Mock
    private ICourseRepository courseRepository;
    @Mock
    private ICourseAdapterService courseAdapter;
    private CourseService courseService;
    private ObjectMapper objectMapper;
    private File courseJson = new File("src/test/resources/jsons/course.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        courseTest = objectMapper.readValue(courseJson, Course.class);
        courseDTOTest = objectMapper.readValue(courseJson, CourseDTO.class);
        courseService = new CourseService(courseRepository, Mappers.getMapper(ICourseMapper.class), courseAdapter);
    }

    @Test
    public void getAllCourses_shouldReturnListOfDTOCourses() {
        List<Course> coursesList = new ArrayList<>();
        coursesList.add(courseTest);
        when(courseRepository.findAll(any(Integer.class), any(Integer.class), any(Boolean.class))).thenReturn(coursesList);

        List<CourseDTO> coursesDTOReturned = courseService.getAllCourses(1,1, Optional.of(true));

        assertEquals(new ArrayList<>(List.of(courseDTOTest)), coursesDTOReturned);
    }

    @Test
    public void getCourseById_givenExistingId_shouldReturnCourseDTO(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));

        CourseDTO courseDTOReturned = courseService.getCourseById(UUID.randomUUID().toString());

        assertEquals(courseDTOTest, courseDTOReturned);
    }

    @Test
    public void getCourseById_givenNonExistingId_shouldThrowException(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThrows(CourseException.class, () -> courseService.getCourseById(UUID.randomUUID().toString()));
    }

    @Test
    public void addCourse_givenValidCourseDTO_shouldReturnCourseDTO(){
        List<Course> coursesList = new ArrayList<>();
        coursesList.add(courseTest);
        when(courseAdapter.saveWithDatabaseFormat(any(CourseDTO.class), any(ICourseRepository.class))).thenReturn(Optional.ofNullable(courseTest));

        CourseDTO courseDTOReturned = courseService.addCourse(courseDTOTest);

        assertEquals(courseDTOTest, courseDTOReturned);
    }

    @Test
    public void addCourse_givenInvalidCourseDTO_shouldThrowException(){
        when(courseAdapter.saveWithDatabaseFormat(any(CourseDTO.class), any(ICourseRepository.class))).thenThrow(new RuntimeException());

        assertThrows(CourseException.class, () -> courseService.addCourse(courseDTOTest));
    }

    @Test
    public void updateCourse_givenValidCourseDTO_shouldReturnCourseDTO(){
        when(courseAdapter.saveWithDatabaseFormat(any(CourseDTO.class), any(ICourseRepository.class))).thenReturn(Optional.ofNullable(courseTest));

        CourseDTO courseDTOReturned = courseService.updateCourse(courseDTOTest);

        assertEquals(courseDTOTest, courseDTOReturned);
    }

    @Test
    public void updateCourse_givenInvalidCourseDTO_shouldThrowException(){
        when(courseAdapter.saveWithDatabaseFormat(any(CourseDTO.class), any(ICourseRepository.class))).thenThrow(new RuntimeException());

        assertThrows(CourseException.class, () -> courseService.updateCourse(courseDTOTest));
    }

    @Test
    public void deleteCourse_givenExistingId_shouldReturnTrue(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenReturn(true);

        Boolean updateActiveExpected = true;
        Boolean updateActiveReturned = courseService.deleteCourse(courseTest.getCourseId().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteCourse_givenNonExistingId_shouldReturnFalse(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenReturn(false);

        Boolean updateActiveExpected = false;
        Boolean updateActiveReturned = courseService.deleteCourse(UUID.randomUUID().toString());

        assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteCourse_givenInvalidId_shouldThrowException(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenThrow(new RuntimeException());

        assertThrows(CourseException.class, () -> courseService.deleteCourse(UUID.randomUUID().toString()));
    }

}



