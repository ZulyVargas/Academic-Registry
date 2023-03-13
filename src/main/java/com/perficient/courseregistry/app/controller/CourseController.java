package com.perficient.courseregistry.app.controller;


import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping( "/api/v1/courses" )
public class CourseController {


    @Autowired
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Set<CourseDTO>> getAllCourses(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                        @RequestParam(name = "offset", defaultValue = "0") Integer offset,
                                                        @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<Set<CourseDTO>>(courseService.getAllCourses(limit, offset, Optional.ofNullable(isActive)),HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id){
        return new ResponseEntity<CourseDTO>(courseService.getCourseById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO){
        return  new ResponseEntity<CourseDTO>(courseService.addCourse(courseDTO), HttpStatus.OK);

    }

}
