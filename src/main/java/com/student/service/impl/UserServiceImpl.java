package com.student.service.impl;

import com.student.dao.UserDao;
import com.student.model.User;
import com.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    
    @Override
    public int register(User user) {
        // 检查用户名是否已存在
        User existingUser = userDao.getUserByUsername(user.getUsername());
        if (existingUser != null) {
            return -1; // 用户名已存在
        }
        
        // 添加用户
        return userDao.addUser(user);
    }
    
    @Override
    public User login(String username, String password) {
        User user = userDao.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }
    
    @Override
    public boolean updateUser(User user) {
        return userDao.updateUser(user);
    }
    
    @Override
    public boolean changePassword(int userId, String oldPassword, String newPassword) {
        User user = userDao.getUserById(userId);
        if (user != null && user.getPassword().equals(oldPassword)) {
            return userDao.updatePassword(userId, newPassword);
        }
        return false;
    }
    
    @Override
    public boolean updateUserAvatar(Integer userId, String avatarPath) {
        return userDao.updateUserAvatar(userId, avatarPath);
    }
}