package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.utils.ROLE;
import com.perficient.courseregistry.app.services.ISubjectService;
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
@RequestMapping( "/api/v1/subjects" )
public class SubjectController {

    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                           @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                           @RequestParam(name = "active", required = false) Boolean isActive,
                                                           @RequestParam(name = "title", required = false) String title) {
        return new ResponseEntity<List<SubjectDTO>>(subjectService.getAllSubjects(limit, offset, Optional.ofNullable(isActive), Optional.ofNullable(title)).stream().peek(SubjectDTO::generateLinks).collect(Collectors.toList()), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @GetMapping(value="/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id){
        return new ResponseEntity<SubjectDTO>(subjectService.getSubjectById(id).generateLinks(), HttpStatus.OK);
    }
    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @PostMapping
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return  new ResponseEntity<SubjectDTO>(subjectService.addSubject(subjectDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @PutMapping
    public ResponseEntity<SubjectDTO>  updateSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return new ResponseEntity<SubjectDTO>(subjectService.updateSubject(subjectDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @DeleteMapping(value="/{subjectId}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String subjectId){
        return new ResponseEntity<Boolean>(subjectService.deleteSubject(subjectId), HttpStatus.OK);
    }

}
