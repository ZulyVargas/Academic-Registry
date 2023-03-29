package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Professor;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IProfessorMapper;
import com.perficient.courseregistry.app.repository.IProfessorRepository;
import com.perficient.courseregistry.app.services.IProfessorService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProfessorService extends UserService implements IProfessorService {

    private final IProfessorRepository professorRepository;
    private final IProfessorMapper professorMapper;

    public ProfessorService(IProfessorRepository professorRepository, IProfessorMapper professorMapper) {
        super();
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Override
    public List<ProfessorDTO> getAllProfessors(Integer limit, Integer offset, Optional<Boolean> isActive, Optional<String> degree ){
        int initial = offset== 1 ? 0 : limit*(offset-1);
        if (degree.isPresent()) return getProfessorsByDegree(limit, initial, isActive.orElse(true), degree.get());
        List<Professor> temp = professorRepository.findAll(limit, initial, isActive.orElse(true));
        return professorRepository.findAll(limit, initial, isActive.orElse(true) )
                                  .stream()
                                  .map(professor -> professorMapper.professorToProfessorDto(professor))
                                  .collect(Collectors.toList());
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
    public List<ProfessorDTO> getProfessorsByDegree(Integer limit, Integer initial, boolean isActive, String degree) {
        return professorRepository.findAllByDegree(limit, initial, isActive, degree).stream()
                                  .map(professor -> professorMapper.professorToProfessorDto(professor))
                                  .collect(Collectors.toList());
    }

    @Override
    public ProfessorDTO addProfessor(ProfessorDTO professorDTO) {
        try{
            UserDTO tempUser = insertProfessorAsUser(professorDTO);
            professorDTO.setUserId(tempUser.getUserId());
            professorRepository.save(professorDTO.getUserId(), professorDTO.getDegree());
        } catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "Professor");
        }
        return getProfessorById(professorDTO.getUserId().toString());
    }

    @Override
    public ProfessorDTO updateProfessor(ProfessorDTO professorDTO) {
        try{
            insertProfessorAsUser(professorDTO);
            professorRepository.update(professorDTO.getUserId(), professorDTO.getDegree());
        } catch (Exception ex){
            throw new UserException(UserException.USER_UPDATE_EXCEPTION, "Professor");
        }
        return getProfessorById(professorDTO.getUserId().toString());
    }

    @Override
    public Boolean deleteProfessor(String userId) {
        return deleteUser(userId);
    }

    private UserDTO insertProfessorAsUser(ProfessorDTO professorDTO){
        return addUser(professorMapper.professorDTOToUserDTO(professorDTO));
    }
}
