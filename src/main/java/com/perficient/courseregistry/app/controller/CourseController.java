package com.perficient.courseregistry.app.controller;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.utils.ROLE;
import com.perficient.courseregistry.app.services.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
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
@RequestMapping( "/api/v1/courses" )
public class CourseController {

    @Autowired
    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN, ROLE.PROFESSOR, ROLE.STUDENT})
    @GetMapping
    public ResponseEntity<List<CourseDTO>> getAllCourses(@RequestParam(name = "limit", defaultValue = "10")  Integer limit,
                                                         @RequestParam(name = "offset", defaultValue = "1") Integer offset,
                                                         @RequestParam(name = "active", required = false) Boolean isActive){
        return new ResponseEntity<List<CourseDTO>>(courseService.getAllCourses(limit, offset, Optional.ofNullable(isActive)).stream().peek(CourseDTO::generateLinks).collect(Collectors.toList()),HttpStatus.OK);
    }
    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN, ROLE.PROFESSOR})
    @GetMapping(value="/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable String id){
        return new ResponseEntity<CourseDTO>(courseService.getCourseById(id).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody @Valid CourseDTO courseDTO){
        return  new ResponseEntity<CourseDTO>(courseService.addCourse(courseDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @PutMapping
    public ResponseEntity<CourseDTO> updatedCourse(@RequestBody @Valid CourseDTO courseDTO){
        return  new ResponseEntity<CourseDTO>(courseService.updateCourse(courseDTO).generateLinks(), HttpStatus.OK);
    }

    @RolesAllowed({ROLE.DEAN, ROLE.ADMIN})
    @DeleteMapping(value="/{courseId}")
    public ResponseEntity<Boolean> deleteCourse(@PathVariable String courseId){
        return  new ResponseEntity<Boolean>(courseService.deleteCourse(courseId), HttpStatus.OK);
    }
}
