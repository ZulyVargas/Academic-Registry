package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

public interface IStudentService {
    Set<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive);

    StudentDTO getStudentById(String studentId);

    StudentDTO addStudent (StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    Boolean deleteStudent (String userId);


}
