package com.perficient.courseregistry.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.services.impl.ProfessorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProfessorController.class)
public class ProfessorControllerTest {
    @MockBean
    private ProfessorService professorService;
    private ProfessorDTO professorDTOTest;

    @BeforeEach
    public void setUp(){
        professorDTOTest = ProfessorDTO.builder().userId(UUID.randomUUID())
                .name("USER TEST")
                .email("usertest@test.edu")
                .gender("F")
                .username("user.test")
                .active(true)
                .degree("TEST")
                .build();
    }

    @Test
    public void getAllProfessors_shouldReturnSetOfDTOProfessors() throws Exception {
        Set<ProfessorDTO> professorDTOSet = new HashSet<>();
        professorDTOSet.add(professorDTOTest);
        when(professorService.getAllProfessors(any(), any(), any())).thenReturn(professorDTOSet);


    }


}

