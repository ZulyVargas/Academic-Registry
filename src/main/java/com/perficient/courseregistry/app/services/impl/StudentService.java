package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.mappers.IStudentMapper;
import com.perficient.courseregistry.app.repository.IStudentRepository;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService  implements IStudentService {

    @Autowired
    private final IStudentRepository studentRepository;

    @Autowired
    private final IStudentMapper studentMapper;

    public StudentService(IStudentRepository studentRepository, IStudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Set<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive) {
        return studentRepository.findAll(limit,offset, isActive.orElse(true))
                                .stream()
                                .map(student -> studentMapper.studentToStudentDto(student))
                                .collect(Collectors.toSet());
    }
}
