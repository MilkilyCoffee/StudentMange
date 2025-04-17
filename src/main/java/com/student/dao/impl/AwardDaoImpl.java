package com.student.dao.impl;

import com.student.dao.AwardDao;
import com.student.model.Award;
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
public class AwardDaoImpl implements AwardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Award> getAwardsByUserId(Integer userId) {
        String sql = "SELECT * FROM award WHERE user_id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Award.class), userId);
    }

    @Override
    public Award getAwardById(Integer id) {
        String sql = "SELECT * FROM award WHERE id = ?";
        List<Award> awards = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Award.class), id);
        return awards.isEmpty() ? null : awards.get(0);
    }

    @Override
    public int addAward(Award award) {
        final String sql = "INSERT INTO award (user_id, award_name, award_level, issuer, award_date, description) VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, award.getUserId());
                ps.setString(2, award.getAwardName());
                ps.setString(3, award.getAwardLevel());
                ps.setString(4, award.getIssuer());
                ps.setDate(5, award.getAwardDate() != null ? new java.sql.Date(award.getAwardDate().getTime()) : null);
                ps.setString(6, award.getDescription());
                return ps;
            }
        }, keyHolder);
        
        Number key = keyHolder.getKey();
        return key != null ? key.intValue() : 0;
    }

    @Override
    public int updateAward(Award award) {
        String sql = "UPDATE award SET award_name = ?, award_level = ?, issuer = ?, award_date = ?, description = ? WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, 
                award.getAwardName(), 
                award.getAwardLevel(), 
                award.getIssuer(), 
                award.getAwardDate(), 
                award.getDescription(), 
                award.getId(), 
                award.getUserId());
    }

    @Override
    public int deleteAward(Integer id, Integer userId) {
        String sql = "DELETE FROM award WHERE id = ? AND user_id = ?";
        return jdbcTemplate.update(sql, id, userId);
    }
}