package com.perficient.courseregistry.app.utils.adapters;

import com.perficient.courseregistry.app.dto.CourseDTO;
import com.perficient.courseregistry.app.entities.Course;
import java.util.List;
public class ComparatorCourse {
    public static boolean findEqualsCourse(List<Course> courses, CourseDTO course){
        boolean result;
        return courses.parallelStream().anyMatch(c -> c.getSubject().getSubjectId().equals(course.getSubject().getSubjectId()) &&
                                     c.getGroupNumber().equals(course.getGroupNumber()) &&
                                     c.getYear().equals(course.getYear())  &&
                                     c.getPeriod().equals(course.getPeriod()));
    }
}
