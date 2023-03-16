package utils.adapters;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.enums.PERIOD;
import com.perficient.courseregistry.app.enums.STATUS_COURSE;
import com.perficient.courseregistry.app.mappers.CourseMapperTest;
import com.perficient.courseregistry.app.mappers.ICourseDetailsMapper;
import com.perficient.courseregistry.app.mappers.ICourseMapper;
import com.perficient.courseregistry.app.mappers.IStudentMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.utils.adapters.CourseAdapterService;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CourseAdapterServiceTest {
    @Mock
    private ICourseRepository courseRepository;
    private  ICourseDetailsMapper courseDetailsMapper = Mappers.getMapper(ICourseDetailsMapper.class);
    private CourseAdapterService courseAdapterService;
    private CourseDTO courseDTOTest;
    private CourseDetails courseDetailsTest;

    private Course courseTest;

    @Before
    public void setUp(){
        ProfessorDTO professorDTO = ProfessorDTO.builder().userId(UUID.randomUUID()).name("USER TEST").email("usertest@test.edu")
                .gender("F").username("user.test").active(true).degree("TEST").build();

        SubjectDTO subjectDTOTest = SubjectDTO.builder().subjectId(UUID.randomUUID()).title("SUBJECT TEST").code("SUBT").credits(4)
                .active(true).prerrequisites(new HashSet<>()).build();

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

        courseTest = Mappers.getMapper(ICourseMapper.class).courseDTOToCourse(courseDTOTest);

        courseDetailsTest= courseDetailsMapper.courseDTOToCourseDetails(courseDTOTest);

        courseAdapterService = new CourseAdapterService(courseDetailsMapper);
    }

    @Test
    public void saveWithDatabaseFormat_givenCourseDTO_shouldReturnCourseDTO(){
        when(courseRepository.save(any(CourseDetails.class))).thenReturn(courseDetailsTest);
        when(courseRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(courseTest));

        Optional<Course> courseDTOReturned = courseAdapterService.saveWithDatabaseFormat(courseDTOTest, courseRepository);

        assertEquals(courseTest,courseDTOReturned.orElse(null));
    }
}
