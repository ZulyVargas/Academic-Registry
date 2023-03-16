package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
                                                          @RequestParam(name = "active", required = false) Boolean isActive) {
        return new ResponseEntity<List<SubjectDTO>>(subjectService.getAllSubjects(limit, offset, Optional.ofNullable(isActive)), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id){
        return new ResponseEntity<SubjectDTO>(subjectService.getSubjectById(id), HttpStatus.OK);
    }
    @GetMapping(value="/title")
    public ResponseEntity<List<SubjectDTO>> getSubjectByTitle(@RequestParam(name = "title") String title){
        return new ResponseEntity<List<SubjectDTO>>(subjectService.getSubjectByTitle(title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return  new ResponseEntity<SubjectDTO>(subjectService.addSubject(subjectDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO>  updateSubject(@RequestBody @Valid SubjectDTO subjectDTO){
        return new ResponseEntity<SubjectDTO>(subjectService.updateSubject(subjectDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{subjectId}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String subjectId){
        return new ResponseEntity<Boolean>(subjectService.deleteSubject(subjectId), HttpStatus.OK);
    }

}
