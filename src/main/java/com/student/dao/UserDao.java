package com.student.dao;

import com.student.model.User;

public interface UserDao {
    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户对象
     */
    User getUserById(Integer id);
    
    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户对象
     */
    User getUserByUsername(String username);
    
    /**
     * 添加用户
     * @param user 用户对象
     * @return 影响的行数
     */
    int addUser(User user);
    
    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 是否更新成功
     */
    boolean updateUser(User user);
    
    /**
     * 更新用户密码
     * @param id 用户ID
     * @param newPassword 新密码
     * @return 是否更新成功
     */
    boolean updatePassword(Integer id, String newPassword);
    
    /**
     * 更新用户头像
     * @param userId 用户ID
     * @param avatarPath 头像路径
     * @return 是否更新成功
     */
    boolean updateUserAvatar(Integer userId, String avatarPath);
}