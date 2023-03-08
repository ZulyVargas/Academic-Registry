package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.ProfessorDTO;

import java.util.Optional;
import java.util.Set;

public interface IProfessorService {

    Set<ProfessorDTO> getAllProfessors(Integer limit, Integer offset, Optional<Boolean> isActive );

    Set<ProfessorDTO> getProfessorsByDegree(String degree);
    
}
