package com.perficient.courseregistry.app.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import com.perficient.courseregistry.app.services.impl.SubjectService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(SubjectController.class)
public class SubjectControllerTest {

    @Autowired
    private MockMvc  mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SubjectService subjectService;
    private SubjectDTO subjectDTOTest;


    @BeforeEach
    public void setUp() {
        subjectDTOTest = SubjectDTO.builder()
                .subjectId(UUID.fromString("d45e4121-cfd0-4307-813e-a50b3d7ea7b5"))
                .title("SUBJECT TEST")
                .code("SUBT")
                .credits(4)
                .active(true)
                .prerrequisites(new HashSet<>())
                .build();
    }

    @Test
    public void getAllSubjects_shouldReturnSetOfDTOSubjects() throws Exception {
        Set<SubjectDTO> subjectDTOSet = new HashSet<>();
        subjectDTOSet.add(subjectDTOTest);
        when(subjectService.getAllSubjects(any(), any(), any())).thenReturn(subjectDTOSet);

        ResultActions response = mockMvc.perform(get("/api/v1/subjects")
                                        .param("limit","1")
                                        .param("offset", "10")
                                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void getSubjectByTitle_givenTitle_shouldReturnSubjectDTO() throws Exception {
        when(subjectService.getSubjectByTitle(anyString())).thenReturn(subjectDTOTest);

        ResultActions response = mockMvc.perform(get("/api/v1/subjects/title/SUBT")
                                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").exists());
    }

    @Test
    public void getSubjectById_givenId_shouldReturnSubjectDTO() throws Exception{
        when(subjectService.getSubjectById(anyString())).thenReturn(subjectDTOTest);

        ResultActions response = mockMvc.perform(get("/api/v1/subjects/1234")
                                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.title").exists());
    }

    @Test
    public void addSubject_givenSubjectDTO_shouldReturnSubjectDTO() throws Exception {
        when(subjectService.addSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);

        ResultActions response = mockMvc.perform(post("/api/v1/subjects")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(subjectDTOTest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(subjectDTOTest.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(subjectDTOTest.getCode())));
    }

    @Test
    public void updateSubject_givenSubjectDTO_shouldReturnSubjectDTO() throws Exception{
        when(subjectService.updateSubject(any(SubjectDTO.class))).thenReturn(subjectDTOTest);
        subjectDTOTest.setTitle("New title");

        ResultActions response = mockMvc.perform(put("/api/v1/subjects")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(subjectDTOTest)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(subjectDTOTest.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", CoreMatchers.is(subjectDTOTest.getCode())));
    }

    @Test
    public void deleteSubject_givenId_shouldReturnBoolean() throws Exception {
        when(subjectService.deleteSubject(anyString())).thenReturn(true);

        ResultActions response = mockMvc.perform(delete("/api/v1/subjects/1234")
                                        .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$").isBoolean());
    }

}
