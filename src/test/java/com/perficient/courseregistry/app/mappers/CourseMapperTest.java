package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.entities.Subject;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;
import java.util.HashSet;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CourseMapperTest {

    private final ICourseMapper courseMapperTest = Mappers.getMapper(ICourseMapper.class);
    private Course courseTest;
    private CourseDTO courseDTOTest;

    @Before
    public void setUp() {
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
        courseTest.setStatusCourse(STATUS_COURSE.CLOSED);
        courseTest.setYear("2022");
        courseTest.setPeriod(PERIOD.valueOf("I"));
        courseTest.setActive(false);

        courseDTOTest = new CourseDTO();
        courseDTOTest.setCourseId(courseTest.getCourseId());
        courseDTOTest.setGroupNumber("1");
        courseDTOTest.setQuota(20);
        courseDTOTest.setProfessor(professorDTO);
        courseDTOTest.setSubject(subjectDTOTest);
        courseDTOTest.setStatusCourse(STATUS_COURSE.CLOSED);
        courseDTOTest.setYear("2022");
        courseDTOTest.setPeriod(PERIOD.valueOf("I"));
        courseDTOTest.setActive(false);
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
