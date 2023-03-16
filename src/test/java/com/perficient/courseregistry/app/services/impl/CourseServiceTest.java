package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import com.perficient.courseregistry.app.exception.custom.CourseException;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.utils.adapters.ICourseAdapterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;

import static org.junit.Assert.*;
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

    @Before
    public void setUp(){
        Professor professor = Professor.builder().userId(UUID.randomUUID()).name("USER TEST").email("usertest@test.edu")
                .gender("F").username("user.test").active(true).degree("TEST").build();
        ProfessorDTO professorDTO = ProfessorDTO.builder().userId(UUID.randomUUID()).name("USER TEST").email("usertest@test.edu")
                .gender("F").username("user.test").active(true).degree("TEST").build();

        Subject subject = new Subject();
        subject.setSubjectId(UUID.randomUUID());
        subject.setTitle("SUBJECT TEST");
        subject.setCode("SUBT");
        subject.setCredits(4);
        subject.setActive(true);
        subject.setPrerrequisites(new HashSet<>());
        SubjectDTO subjectDTOTest = SubjectDTO.builder().subjectId(subject.getSubjectId()).title("SUBJECT TEST").code("SUBT").credits(4)
                .active(true).prerrequisites(new HashSet<>()).build();

        courseTest = new Course();
        courseTest.setCourseId(UUID.randomUUID());
        courseTest.setGroupNumber("1");
        courseTest.setQuota(20);
        courseTest.setProfessor(professor);
        courseTest.setSubject(subject);
        courseTest.setStatusCourse(STATUS_COURSE.IN_PROGRESS);
        courseTest.setYear("2022");
        courseTest.setPeriod(PERIOD.valueOf("I"));
        courseTest.setActive(true);

        courseDTOTest = new CourseDTO();
        courseDTOTest.setCourseId(courseTest.getCourseId());
        courseDTOTest.setGroupNumber("1");
        courseDTOTest.setQuota(20);
        courseDTOTest.setProfessor(professorDTO);
        courseDTOTest.setSubject(subjectDTOTest);
        courseDTOTest.setStatusCourse(STATUS_COURSE.IN_PROGRESS);
        courseDTOTest.setYear("2022");
        courseDTOTest.setPeriod(PERIOD.valueOf("I"));
        courseDTOTest.setActive(true);

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
    public void deleteSubject_givenExistingId_shouldReturnTrue(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenReturn(true);

        Boolean updateActiveExpected = true;
        Boolean updateActiveReturned = courseService.deleteCourse(UUID.randomUUID().toString());

        Assert.assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteSubject_givenNonExistingId_shouldReturnFalse(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenReturn(false);

        Boolean updateActiveExpected = false;
        Boolean updateActiveReturned = courseService.deleteCourse(UUID.randomUUID().toString());

        Assert.assertEquals(updateActiveExpected, updateActiveReturned);
    }

    @Test
    public void deleteSubject_givenInvalidId_shouldThrowException(){
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));
        when(courseRepository.updateActive(any(UUID.class))).thenThrow(new RuntimeException());

        Assert.assertThrows(CourseException.class, () -> courseService.deleteCourse(UUID.randomUUID().toString()));
    }

}



