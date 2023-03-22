package com.perficient.courseregistry.app.services.impl;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.entities.Student;
import com.perficient.courseregistry.app.exception.custom.UserException;
import com.perficient.courseregistry.app.mappers.IStudentMapper;
import com.perficient.courseregistry.app.repository.IStudentRepository;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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

    public List<StudentDTO> getAllStudents(Integer limit, Integer offset, Optional<Boolean> isActive) {
        int initial = offset== 1 ? 0 : limit*(offset-1);
        return studentRepository.findAll(limit,initial, isActive.orElse(true))
                                .stream()
                                .map(student -> studentMapper.studentToStudentDto(student))
                                .collect(Collectors.toList());
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
            UserDTO tempUser = insertStudentAsUser(studentDTO);
            studentDTO.setUserId(tempUser.getUserId());
            studentRepository.save(studentDTO.getUserId(), studentDTO.getAvg(), studentDTO.getStatus());
        } catch (Exception ex){
            throw new UserException(UserException.USER_INSERT_EXCEPTION, "student");
        }
        return getStudentById(studentDTO.getUserId().toString());
    }

    @Override
    public StudentDTO updateStudent(StudentDTO studentDTO) {
        try {
            UserDTO tempUser = insertStudentAsUser(studentDTO);
            studentDTO.setUserId(tempUser.getUserId());
            studentRepository.update(studentDTO.getUserId(), studentDTO.getAvg(), studentDTO.getStatus());
        } catch (Exception ex){
            throw new UserException(UserException.USER_UPDATE_EXCEPTION, "student");
        }
        return getStudentById(studentDTO.getUserId().toString());
    }

    @Override
    public Boolean deleteStudent(String userId){
        return deleteUser(userId);
    }

    private UserDTO insertStudentAsUser(StudentDTO studentDTO){
        return addUser(studentMapper.studentDTOToUser(studentDTO));
    }
}