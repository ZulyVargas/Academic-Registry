package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.SubjectDTO;
import com.perficient.courseregistry.app.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/subjects" )
public class SubjectController {

    @Autowired
    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<SubjectDTO>> getAllCourses(){
        return new ResponseEntity<List<SubjectDTO>>(subjectService.getAllSubjects(), HttpStatus.OK);
    }
}
