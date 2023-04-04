package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllSubjects(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                           @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                           @RequestParam(name = "active", required = false) Boolean isActive,
                                                           @RequestParam(name = "title", required = false) String title) {
        return new ResponseEntity<List<SubjectDTO>>(subjectService.getAllSubjects(limit, offset, Optional.ofNullable(isActive), Optional.ofNullable(title)).stream().peek(SubjectDTO::generateLinks).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id){
        return new ResponseEntity<SubjectDTO>(subjectService.getSubjectById(id).generateLinks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return  new ResponseEntity<SubjectDTO>(subjectService.addSubject(subjectDTO).generateLinks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO>  updateSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return new ResponseEntity<SubjectDTO>(subjectService.updateSubject(subjectDTO).generateLinks(), HttpStatus.OK);
    }

    @DeleteMapping(value="/{subjectId}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String subjectId){
        return new ResponseEntity<Boolean>(subjectService.deleteSubject(subjectId), HttpStatus.OK);
    }

}
