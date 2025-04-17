package com.student.dao.impl;

import com.student.dao.UserDao;
import com.student.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserById(Integer id) {
        String sql = "SELECT * FROM user WHERE id = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), id);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username = ?";
        List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class), username);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public int addUser(User user) {
        String sql = "INSERT INTO user (username, password, real_name, gender, birth_date, phone, email, address, create_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
        return jdbcTemplate.update(sql, 
                user.getUsername(), 
                user.getPassword(), 
                user.getRealName(), 
                user.getGender(), 
                user.getBirthDate(), 
                user.getPhone(), 
                user.getEmail(), 
                user.getAddress());
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET real_name = ?, gender = ?, birth_date = ?, phone = ?, email = ?, address = ?, update_time = NOW() WHERE id = ?";
        int result = jdbcTemplate.update(sql, 
                user.getRealName(), 
                user.getGender(), 
                user.getBirthDate(), 
                user.getPhone(), 
                user.getEmail(), 
                user.getAddress(), 
                user.getId());
        return result > 0;
    }

    @Override
    public boolean updatePassword(Integer id, String newPassword) {
        String sql = "UPDATE user SET password = ?, update_time = NOW() WHERE id = ?";
        int result = jdbcTemplate.update(sql, newPassword, id);
        return result > 0;
    }
    
    @Override
    public boolean updateUserAvatar(Integer userId, String avatarPath) {
        String sql = "UPDATE user SET avatar = ?, update_time = NOW() WHERE id = ?";
        int result = jdbcTemplate.update(sql, avatarPath, userId);
        return result > 0;
    }
}