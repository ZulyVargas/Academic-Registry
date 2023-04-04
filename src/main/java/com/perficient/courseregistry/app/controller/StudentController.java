package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.utils.ROLE;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/v1/students" )
public class StudentController {

    private final IStudentService studentService;
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                           @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                           @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<>(studentService.getAllStudents(limit, offset, Optional.ofNullable(isActive)).stream().peek(StudentDTO::generateLinks).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @GetMapping(value="/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id){
        return new ResponseEntity<>(studentService.getStudentById(id).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.ADMIN})
    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody @Valid StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.addStudent(studentDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.ADMIN})
    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.updateStudent(studentDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.ADMIN})
    @DeleteMapping(value="/{studentId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String studentId){
        return new ResponseEntity<Boolean>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }
}
