package com.perficient.courseregistry.app.services;

import com.perficient.courseregistry.app.dto.StudentDTO;

import java.util.Optional;
import java.util.Set;

public interface IStudentService {
    Set<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive);
}
