package com.student.dao;

import com.student.model.Education;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class EducationDao {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // 根据用户ID获取学历信息列表
    public List<Education> findByUserId(Integer userId) {
        String sql = "SELECT * FROM education WHERE user_id = ? ORDER BY start_date DESC";
        return jdbcTemplate.query(sql, new EducationRowMapper(), userId);
    }
    
    // 根据ID获取学历信息
    public Education findById(Integer id) {
        String sql = "SELECT * FROM education WHERE id = ?";
        List<Education> educations = jdbcTemplate.query(sql, new EducationRowMapper(), id);
        return educations.isEmpty() ? null : educations.get(0);
    }
    
    // 添加学历信息
    public int addEducation(Education education) {
        String sql = "INSERT INTO education (user_id, school, major, degree, start_date, end_date, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, education.getUserId());
            ps.setString(2, education.getSchool());
            ps.setString(3, education.getMajor());
            ps.setString(4, education.getDegree());
            ps.setDate(5, education.getStartDate() != null ? new java.sql.Date(education.getStartDate().getTime()) : null);
            ps.setDate(6, education.getEndDate() != null ? new java.sql.Date(education.getEndDate().getTime()) : null);
            ps.setString(7, education.getDescription());
            return ps;
        }, keyHolder);
        
        return keyHolder.getKey() != null ? keyHolder.getKey().intValue() : 0;
    }
    
    // 更新学历信息
    public int updateEducation(Education education) {
        String sql = "UPDATE education SET school = ?, major = ?, degree = ?, " +
                     "start_date = ?, end_date = ?, description = ? WHERE id = ? AND user_id = ?";
        
        return jdbcTemplate.update(sql, 
                education.getSchool(), 
                education.getMajor(), 
                education.getDegree(), 
                education.getStartDate() != null ? new java.sql.Date(education.getStartDate().getTime()) : null,
                education.getEndDate() != null ? new java.sql.Date(education.getEndDate().getTime()) : null,
                education.getDescription(),
                education.getId(),
                education.getUserId());
    }
    
    // 删除学历信息
    public int deleteEducation(Integer id, Integer userId) {
        String sql = "DELETE FROM education WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, id, userId);
    }
    
    // RowMapper实现
    private static class EducationRowMapper implements RowMapper<Education> {
        @Override
        public Education mapRow(ResultSet rs, int rowNum) throws SQLException {
            Education education = new Education();
            education.setId(rs.getInt("id"));
            education.setUserId(rs.getInt("user_id"));
            education.setSchool(rs.getString("school"));
            education.setMajor(rs.getString("major"));
            education.setDegree(rs.getString("degree"));
            education.setStartDate(rs.getDate("start_date"));
            education.setEndDate(rs.getDate("end_date"));
            education.setDescription(rs.getString("description"));
            return education;
        }
    }
}