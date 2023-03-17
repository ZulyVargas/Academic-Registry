package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseDetailsMapperTest {
    private final ICourseDetailsMapper courseDetailsMapper = Mappers.getMapper(ICourseDetailsMapper.class);
    private CourseDTO courseDTOTest;
    private CourseDetails courseDetailsTest;

    @Before
    public void setUp() {
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
        courseDTOTest.setStatusCourse(STATUS_COURSE.CLOSED);
        courseDTOTest.setYear("2022");
        courseDTOTest.setPeriod(PERIOD.valueOf("I"));
        courseDTOTest.setActive(false);

        courseDetailsTest = CourseDetails.builder().courseId(courseDTOTest.getCourseId()).quota(20).professorId(professorDTO.getUserId()).subjectId(courseDTOTest.getSubject().getSubjectId())
                                         .statusCourse(courseDTOTest.getStatusCourse()).groupNumber("1").year(courseDTOTest.getYear()).period(courseDTOTest.getPeriod()).active(false).build();
    }

    @Test
    public void courseDetailsToCourseDTO(){
        assertEquals(courseDetailsTest,courseDetailsMapper.courseDTOToCourseDetails(courseDTOTest));
    }

}
