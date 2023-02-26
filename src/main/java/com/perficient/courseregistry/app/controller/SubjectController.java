package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping( "/api/v1/subjects" )
public class SubjectController {

    @Autowired
    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<Set<SubjectDTO>> getAllSubjects(){
        return new ResponseEntity<Set<SubjectDTO>>(subjectService.getAllSubjects(), HttpStatus.OK);
    }

    @GetMapping(value="/paged/{limit}/{offset}")
    public ResponseEntity<Set<SubjectDTO>> getAllSubjectsPaged(@PathVariable Integer limit, @PathVariable Integer offset){
        return new ResponseEntity<Set<SubjectDTO>>(subjectService.getAllSubjectsPaged(limit, offset), HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<SubjectDTO> getSubjectById(@PathVariable String id){
        return new ResponseEntity<SubjectDTO>(subjectService.getSubjectById(id), HttpStatus.OK);
    }
    @GetMapping(value="/title/{title}")
    public ResponseEntity<SubjectDTO> getSubjectByTitle(@PathVariable String title){
        return new ResponseEntity<SubjectDTO>(subjectService.getSubjectByTitle(title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SubjectDTO> addSubject(@RequestBody SubjectDTO subjectDTO){
        return  new ResponseEntity<SubjectDTO>(subjectService.addSubject(subjectDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SubjectDTO>  updateSubject(@RequestBody SubjectDTO subjectDTO){
        return new ResponseEntity<SubjectDTO>(subjectService.updateSubject(subjectDTO), HttpStatus.OK);
    }

    @DeleteMapping(value="/{subjectId}")
    public ResponseEntity<Boolean> deleteSubject(@PathVariable String subjectId){
        return new ResponseEntity<Boolean>(subjectService.deleteSubject(subjectId), HttpStatus.OK);
    }
}
