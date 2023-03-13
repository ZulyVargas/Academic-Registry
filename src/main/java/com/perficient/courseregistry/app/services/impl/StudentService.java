package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Student;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IStudentMapper;
import com.perficient.courseregistry.app.repository.IStudentRepository;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService extends UserService implements IStudentService {

    private final IStudentRepository studentRepository;
    private final IStudentMapper studentMapper;

    public StudentService(IStudentRepository studentRepository, IStudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Set<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive) {
        return studentRepository.findAll(limit,offset, isActive.orElse(true))
                                .stream()
                                .map(student -> studentMapper.studentToStudentDto(student))
                                .collect(Collectors.toSet());
    }

    @Override
    public StudentDTO getStudentById(String studentId){
        Optional<Student> student = studentRepository.findById(UUID.fromString(studentId));
        if (student.isPresent()) return studentMapper.studentToStudentDto(student.get());
        else {
            throw new UserException(UserException.USER_ID_EXCEPTION, "ID");
        }
    }

    public StudentDTO addStudent (StudentDTO studentDTO) {
        try {
            UserDTO tempUser = addUser(studentMapper.studentDTOToUser(studentDTO));
            studentDTO.setUserId(tempUser.getUserId());
            studentRepository.save(studentDTO.getUserId(), studentDTO.getAvg(), studentDTO.getStatus());
        } catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "student");
        }
        return getStudentById(studentDTO.getUserId().toString());
    }

    @Override
    public Boolean deleteStudent(String userId){
        return deleteUser(userId);
    }
}