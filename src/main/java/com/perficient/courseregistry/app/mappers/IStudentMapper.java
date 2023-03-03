package com.perficient.courseregistry.app.mappers;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.entities.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IStudentMapper {

    IStudentMapper INSTANCE = Mappers.getMapper(IStudentMapper.class);

    StudentDTO studentToStudentDto(Student student);

    Student studentDtoToStudent(StudentDTO studentDTO);


}
