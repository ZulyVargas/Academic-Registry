package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.ProfessorDTO;

import java.util.Set;

public interface IProfessorService {

    Set<ProfessorDTO> getAllProfessors();
}
