package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.services.impl.CourseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseControllerTest {
     @Mock
     private CourseService courseService;
    private CourseDTO courseDTOTest;

    private CourseController courseController;

    @Before
    public void setUp(){
        ProfessorDTO professorDTO = ProfessorDTO.builder().userId(UUID.randomUUID()).name("USER TEST").email("usertest@test.edu")
                .gender("F").username("user.test").active(true).degree("TEST").build();

        SubjectDTO subjectDTOTest = SubjectDTO.builder().subjectId(UUID.randomUUID()).title("SUBJECT TEST").code("SUBT").credits(4)
                .active(true).prerequisites(new HashSet<>()).build();

        courseDTOTest = new CourseDTO();
        courseDTOTest.setCourseId(UUID.randomUUID());
        courseDTOTest.setGroupNumber("1");
        courseDTOTest.setQuota(20);
        courseDTOTest.setProfessor(professorDTO);
        courseDTOTest.setSubject(subjectDTOTest);
        courseDTOTest.setStatusCourse(STATUS_COURSE.IN_PROGRESS);
        courseDTOTest.setYear("2022");
        courseDTOTest.setPeriod(PERIOD.valueOf("I"));
        courseDTOTest.setActive(true);

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
