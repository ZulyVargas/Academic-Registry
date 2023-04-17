package com.perficient.courseregistry.app.mappers;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.assertEquals;

public class SubjectMapperTest {

    private final ISubjectMapper subjectMapperTest = Mappers.getMapper(ISubjectMapper.class);
    private Subject subjectTest;
    private SubjectDTO subjectDTOTest;
    private ObjectMapper objectMapper;
    private File subjectJson = new File("src/test/resources/jsons/subject.json");

    @Before
    public void setUp() throws IOException {
        objectMapper = new ObjectMapper();
        subjectTest = objectMapper.readValue(subjectJson, Subject.class);
        subjectDTOTest = objectMapper.readValue(subjectJson, SubjectDTO.class);
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
