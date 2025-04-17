package com.student.service;

import com.student.model.User;

public interface UserService {
    
    // 用户注册
    int register(User user);
    
    // 用户登录
    User login(String username, String password);
    
    // 根据ID获取用户信息
    User getUserById(int id);
    
    // 更新用户信息
    boolean updateUser(User user);
    
    // 修改密码
    boolean changePassword(int userId, String oldPassword, String newPassword);
    
    // 更新用户头像
    boolean updateUserAvatar(Integer userId, String avatarPath);
}