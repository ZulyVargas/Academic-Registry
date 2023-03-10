package com.perficient.courseregistry.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.entities.Subject;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.when;
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
}
