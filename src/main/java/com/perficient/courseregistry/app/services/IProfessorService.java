package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.services.impl.ProfessorService;

import java.util.Optional;
import java.util.Set;

public interface IProfessorService {

    Set<ProfessorDTO> getAllProfessors(Integer limit, Integer offset, Optional<Boolean> isActive );

    ProfessorDTO getProfessorById(String id);

    Set<ProfessorDTO> getProfessorsByDegree(String degree);

    ProfessorDTO addProfessor (ProfessorDTO professorDTO);

    Boolean deleteProfessor (String userId);

}
