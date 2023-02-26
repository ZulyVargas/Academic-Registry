package com.perficient.courseregistry.app.controller;


import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/api/v1/courses" )
public class CourseController {


    @Autowired
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return new ResponseEntity<List<CourseDTO>>(courseService.getAllCourses(),HttpStatus.OK);
    }

    //@GetMapping(value="/courses-paged/{limit}/{offset}")
   // public List<Course> getAllCoursesPaged(@PathVariable Integer limit, @PathVariable Integer offset){
      //  return new ResponseEntity<List<Course>>(courseService.getAllCoursesPaged(limit, offset),HttpStatus.OK);
    //}

    //@PostMapping
    //public Course saveCourse(@RequestBody Course course){
    //    return courseService.saveCourse(course);
    //}

}
