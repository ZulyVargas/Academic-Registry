package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping( "/api/v1/courses" )
public class CourseController {

    @Autowired
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                         @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                         @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<List<CourseDTO>>(courseService.getAllCourses(limit, offset, Optional.ofNullable(isActive)).stream().peek(CourseDTO::generateLinks).collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id){
        return new ResponseEntity<CourseDTO>(courseService.getCourseById(id).generateLinks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO){
        return  new ResponseEntity<CourseDTO>(courseService.addCourse(courseDTO).generateLinks(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<CourseDTO> updatedCourse(@RequestBody @Valid CourseDTO courseDTO){
        return  new ResponseEntity<CourseDTO>(courseService.updateCourse(courseDTO).generateLinks(), HttpStatus.OK);
    }

    @DeleteMapping(value="/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable String courseId){
        return  new ResponseEntity<Boolean>(courseService.deleteCourse(courseId), HttpStatus.OK);
    }
}
