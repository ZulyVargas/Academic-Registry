package utils.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.mappers.ICourseDetailsMapper;
import com.perficient.courseregistry.app.repository.ICourseRepository;
import com.perficient.courseregistry.app.utils.adapters.CourseAdapterService;
import com.perficient.courseregistry.app.utils.adapters.entities.CourseDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.File;
import java.io.IOException;
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
    private ObjectMapper objectMapper;
    private File courseJson = new File("src/test/resources/jsons/course.json");
    private File courseDetailsJson = new File("src/test/resources/jsons/courseDetails.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        courseDTOTest = objectMapper.readValue(courseJson, CourseDTO.class);
        courseDetailsTest = objectMapper.readValue(courseDetailsJson, CourseDetails.class);
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
