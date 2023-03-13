package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IProfessorMapper;
import com.perficient.courseregistry.app.repository.IProfessorRepository;
import com.perficient.courseregistry.app.services.IProfessorService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfessorService extends UserService implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final IProfessorMapper professorMapper;

    public ProfessorService(IProfessorRepository professorRepository, IProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public Set<ProfessorDTO> getAllProfessors(Integer limit, Integer offset, Optional<Boolean> isActive ){
        return professorRepository.findAll(limit, offset, isActive.orElse(true) )
                                  .stream()
                                  .map(professor -> professorMapper.professorToProfessorDto(professor))
                                  .collect(Collectors.toSet());
    }

    @Override
    public ProfessorDTO getProfessorById(String id) {
        Optional<Professor> professor = professorRepository.findById(UUID.fromString(id));
        if(professor.isPresent()) return professorMapper.professorToProfessorDto(professor.get());
        else {
            throw new UserException(UserException.USER_ID_EXCEPTION, "ID");
        }
    }

    @Override
    public Set<ProfessorDTO> getProfessorsByDegree(String degree) {
        System.out.println("degree "+ degree);
        return professorRepository.findAllByDegree(degree).stream()
                                  .map(professor -> professorMapper.professorToProfessorDto(professor))
                                  .collect(Collectors.toSet());

    }

    @Override
    public ProfessorDTO addProfessor(ProfessorDTO professorDTO) {
        try{
        UserDTO tempUser = addUser(professorMapper.professorDTOToUserDTO(professorDTO));
        professorDTO.setUserId(tempUser.getUserId());
        professorRepository.save(professorDTO.getUserId(), professorDTO.getDegree());
        } catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "Professor");
        }
        return getProfessorById(professorDTO.getUserId().toString());
    }

    @Override
    public Boolean deleteProfessor(String userId) {
        return deleteUser(userId);
    }


}
