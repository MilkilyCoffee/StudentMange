package com.student.service;

import com.student.model.Course;
import java.util.List;

public interface CourseService {
    List<Course> getCoursesByUserId(Integer userId);
    Course getCourseById(Integer id);
    int addCourse(Course course);
    boolean updateCourse(Course course);
    boolean deleteCourse(Integer id, Integer userId);
}