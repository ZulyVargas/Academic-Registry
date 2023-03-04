package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.StudentDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.services.IProfessorService;
import com.perficient.courseregistry.app.services.IUserService;
import com.perficient.courseregistry.app.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping( "/api/v1/users" )
public class UserController {

    @Autowired
    private final IUserService userService;
    @Autowired
    private final IProfessorService professorService;
    @Autowired
    private final IStudentService studentService;

    public UserController(IUserService userService, IProfessorService professorService, IStudentService studentService) {
        this.userService = userService;
        this.professorService = professorService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<Set<UserDTO>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }
    @GetMapping(value="/paged/{limit}/{offset}")
    public ResponseEntity<Set<UserDTO>> getAllUsersPaged(@PathVariable Integer limit, @PathVariable Integer offset){
        return new ResponseEntity<>(this.userService.getAllUsersPaged(limit,offset), HttpStatus.OK);
    }
    @GetMapping(value="/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable String userId){
        return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
    }
    @GetMapping(value="/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<>(this.userService.getUserByUsername(username), HttpStatus.OK);
    }

    @GetMapping(value = "/professors")
    public ResponseEntity<Set<ProfessorDTO>> getAllProfessors(){
        return new ResponseEntity<>(this.professorService.getAllProfessors(), HttpStatus.OK);
    }

    @GetMapping(value = "/professors/degree/{degree}")
    public ResponseEntity<Set<ProfessorDTO>> getProffesorsByDegree(@PathVariable String degree){
        return new ResponseEntity<>(this.professorService.getProfessorsByDegree(degree), HttpStatus.OK);
    }

    @GetMapping(value = "/students")
    public ResponseEntity<Set<StudentDTO>> getAllStudents(){
        return new ResponseEntity<>(this.studentService.getAllStudents(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<UserDTO>(this.userService.addUser(userDTO), HttpStatus.OK);
    }

}
