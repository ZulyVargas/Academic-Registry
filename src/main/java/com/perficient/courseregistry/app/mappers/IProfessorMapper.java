package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.entities.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IProfessorMapper {

    ProfessorDTO professorToProfessorDto(Professor professor);
    Professor professorDtoToProfessor(ProfessorDTO professorDTO);
}
