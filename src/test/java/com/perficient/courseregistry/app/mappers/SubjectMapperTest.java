package com.perficient.courseregistry.app.mappers;

import java.util.HashSet;
import java.util.UUID;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubjectMapperTest {

    private final ISubjectMapper subjectMapperTest = Mappers.getMapper(ISubjectMapper.class);
    private Subject subjectTest;
    private SubjectDTO subjectDTOTest;

    @BeforeAll
    public void setUp(){
        subjectTest = new Subject();
        subjectTest.setSubjectId(UUID.fromString("d45e4121-cfd0-4307-813e-a50b3d7ea7b5"));
        subjectTest.setTitle("SUBJECT TEST");
        subjectTest.setCode("SUBT");
        subjectTest.setCredits(4);
        subjectTest.setActive(true);
        subjectTest.setPrerequisites(new HashSet<>());

        subjectDTOTest = SubjectDTO.builder()
                                   .subjectId(UUID.fromString("d45e4121-cfd0-4307-813e-a50b3d7ea7b5"))
                                   .title("SUBJECT TEST")
                                   .code("SUBT")
                                   .credits(4)
                                   .active(true)
                                   .prerequisites(new HashSet<>())
                                   .build();
}
    @Test
    public void subjectToSubjectDTO() {
       assertEquals(subjectDTOTest,subjectMapperTest.subjectToSubjectDTO(subjectTest));
    }
    @Test
    public void setSubjectDTOToSubject(){
        assertEquals(subjectTest,subjectMapperTest.subjectDtoToSubject(subjectDTOTest));
    }

}
