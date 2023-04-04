package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.ProfessorDTO;

import java.util.List;
import java.util.Optional;

public interface IProfessorService {

    List<ProfessorDTO> getAllProfessors(Integer limit, Integer offset, Optional<Boolean> isActive, Optional<String> degree);

    ProfessorDTO getProfessorById(String id);

    List<ProfessorDTO> getProfessorsByDegree(Integer limit, Integer initial, boolean isActive, String degree);

    ProfessorDTO addProfessor (ProfessorDTO professorDTO);

    ProfessorDTO updateProfessor(ProfessorDTO professorDTO);

    Boolean deleteProfessor (String userId);

}
