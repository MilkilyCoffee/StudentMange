package com.student.dao;

import com.student.model.Course;
import java.util.List;

public interface CourseDao {
    /**
     * 根据用户ID获取课程列表
     * @param userId 用户ID
     * @return 课程列表
     */
    List<Course> getCoursesByUserId(Integer userId);
    
    /**
     * 根据ID获取课程信息
     * @param id 课程ID
     * @return 课程信息
     */
    Course getCourseById(Integer id);
    
    /**
     * 添加课程
     * @param course 课程信息
     * @return 影响行数
     */
    int addCourse(Course course);
    
    /**
     * 更新课程信息
     * @param course 课程信息
     * @return 影响行数
     */
    int updateCourse(Course course);
    
    /**
     * 删除课程
     * @param id 课程ID
     * @param userId 用户ID（用于权限验证）
     * @return 影响行数
     */
    int deleteCourse(Integer id, Integer userId);
}