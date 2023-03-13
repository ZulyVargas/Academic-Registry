package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.dto.UserDTO;
import com.perficient.courseregistry.app.services.IProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping( "/api/v1/professors" )
public class ProfessorController {

    private final IProfessorService professorService;

    public ProfessorController(IProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public ResponseEntity<Set<ProfessorDTO>> getAllProfessors(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                              @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                                              @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<>(professorService.getAllProfessors(limit, offset, Optional.ofNullable(isActive)), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> getProfessorById(@PathVariable String id){
        return new ResponseEntity<>(professorService.getProfessorById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/degree/{degree}")
    public ResponseEntity<Set<ProfessorDTO>> getProffesorsByDegree(@PathVariable String degree){
        return new ResponseEntity<>(professorService.getProfessorsByDegree(degree), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody @Valid ProfessorDTO professorDTO){
        return new ResponseEntity<>(professorService.addProfessor(professorDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{professorId}")
    public ResponseEntity<Boolean> deleteProfessor(@PathVariable String professorId){
        return new ResponseEntity<>(professorService.deleteProfessor(professorId), HttpStatus.OK);
    }
}
