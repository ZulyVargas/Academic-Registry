package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.StudentDTO;
import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive);

    StudentDTO getStudentById(String studentId);

    StudentDTO addStudent (StudentDTO studentDTO);

    StudentDTO updateStudent(StudentDTO studentDTO);

    Boolean deleteStudent (String userId);


}
