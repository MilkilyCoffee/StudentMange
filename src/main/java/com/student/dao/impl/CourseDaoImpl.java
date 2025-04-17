package com.student.dao.impl;

import com.student.dao.CourseDao;
import com.student.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Course> getCoursesByUserId(Integer userId) {
        String sql = "SELECT * FROM course WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Course.class), userId);
    }

    @Override
    public Course getCourseById(Integer id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        List<Course> courses = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Course.class), id);
        return courses.isEmpty() ? null : courses.get(0);
    }

    @Override
    public int addCourse(Course course) {
        final String sql = "INSERT INTO course (user_id, course_name, course_code, teacher, credit, semester, grade, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, course.getUserId());
                ps.setString(2, course.getCourseName());
                ps.setString(3, course.getCourseCode());
                ps.setString(4, course.getTeacher());
                ps.setDouble(5, course.getCredit() != null ? course.getCredit() : 0);
                ps.setString(6, course.getSemester());
                ps.setString(7, course.getGrade());
                ps.setString(8, course.getDescription());
                return ps;
            }
        }, keyHolder);
        
        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public int updateCourse(Course course) {
        String sql = "UPDATE course SET course_name = ?, course_code = ?, teacher = ?, credit = ?, semester = ?, grade = ?, description = ? WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, 
                course.getCourseName(), 
                course.getCourseCode(), 
                course.getTeacher(), 
                course.getCredit(), 
                course.getSemester(), 
                course.getGrade(), 
                course.getDescription(), 
                course.getId(), 
                course.getUserId());
    }

    @Override
    public int deleteCourse(Integer id, Integer userId) {
        String sql = "DELETE FROM course WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, id, userId);
    }
}