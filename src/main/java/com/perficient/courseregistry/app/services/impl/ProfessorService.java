package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.mappers.IProfessorMapper;
import com.perficient.courseregistry.app.repository.IProfessorRepository;
import com.perficient.courseregistry.app.services.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorService implements IProfessorService {

    @Autowired
    private final IProfessorRepository professorRepository;

    @Autowired
    private final IProfessorMapper professorMapper;


    public ProfessorService(IProfessorRepository professorRepository, IProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public Set<ProfessorDTO> getAllProfessors(){
        Set<ProfessorDTO> professors = this.professorRepository.findAll().stream()
                .map(user -> professorMapper.professorDtoToProfessor(user))
                .collect(Collectors.toSet());
        return professors;
    }
}
