package com.perficient.courseregistry.app.controller;


import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping( "/api/v1/students" )
public class StudentController {

    private final IStudentService studentService;
    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Set<StudentDTO>> getAllStudents(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                          @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                                          @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<>(studentService.getAllStudents(limit, offset, Optional.ofNullable(isActive)), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getStudentById(@PathVariable String id){
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentDTO> addStudent(@RequestBody @Valid StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.addStudent(studentDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody @Valid StudentDTO studentDTO){
        return new ResponseEntity<>(studentService.updateStudent(studentDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{studentId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable String studentId){
        return new ResponseEntity<Boolean>(studentService.deleteStudent(studentId), HttpStatus.OK);
    }

}
