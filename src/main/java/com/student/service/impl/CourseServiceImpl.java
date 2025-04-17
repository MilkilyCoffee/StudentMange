package com.student.service.impl;

import com.student.dao.CourseDao;
import com.student.model.Course;
import com.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseDao courseDao;

    @Override
    public List<Course> getCoursesByUserId(Integer userId) {
        return courseDao.getCoursesByUserId(userId);
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseDao.getCourseById(id);
    }

    @Override
    public int addCourse(Course course) {
        return courseDao.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        int result = courseDao.updateCourse(course);
        return result > 0;
    }

    @Override
    public boolean deleteCourse(Integer id, Integer userId) {
        int result = courseDao.deleteCourse(id, userId);
        return result > 0;
    }
}