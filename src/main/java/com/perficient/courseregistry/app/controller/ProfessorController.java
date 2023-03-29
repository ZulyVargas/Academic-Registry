package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.ProfessorDTO;
import com.perficient.courseregistry.app.services.IProfessorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping( "/api/v1/professors" )
public class ProfessorController {

    private final IProfessorService professorService;

    public ProfessorController(IProfessorService professorService) {
        this.professorService = professorService;
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> getAllProfessors(@RequestParam(name = "limit",  defaultValue = "10") Integer limit,
                                                               @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                               @RequestParam(name = "active", required = false) Boolean isActive,
                                                               @RequestParam(name = "degree", required = false) String degree){
        return new ResponseEntity<>(professorService.getAllProfessors(limit, offset, Optional.ofNullable(isActive), Optional.ofNullable(degree)).stream().peek(ProfessorDTO::generateLinks).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @GetMapping(value="/{id}")
    public ResponseEntity<ProfessorDTO> getProfessorById(@PathVariable String id){
        return new ResponseEntity<>(professorService.getProfessorById(id).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @PostMapping
    public ResponseEntity<ProfessorDTO> addProfessor(@RequestBody @Valid ProfessorDTO professorDTO){
        return new ResponseEntity<>(professorService.addProfessor(professorDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @PutMapping
    public ResponseEntity<ProfessorDTO> updateProfessor(@RequestBody @Valid ProfessorDTO professorDTO){
        return new ResponseEntity<>(professorService.updateProfessor(professorDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({"ADMIN"})
    @DeleteMapping(value="/{professorId}")
    public ResponseEntity<Boolean> deleteProfessor(@PathVariable String professorId){
        return new ResponseEntity<>(professorService.deleteProfessor(professorId), HttpStatus.OK);
    }
}
