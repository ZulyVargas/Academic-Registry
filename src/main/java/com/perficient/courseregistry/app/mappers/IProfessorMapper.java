package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Professor;
import org.mapstruct.Mapper;


@Mapper
public interface IProfessorMapper {

    ProfessorDTO professorToProfessorDto(Professor professor);
    Professor professorDtoToProfessor(ProfessorDTO professorDTO);
    UserDTO professorDTOToUserDTO(ProfessorDTO professorDTO);
}
