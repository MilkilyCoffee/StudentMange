package com.student.controller;

import com.student.model.User;
import com.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    // 跳转到注册页面
    @GetMapping("/register")
    public String registerPage() {
        return "user/register";
    }
    
    // 处理注册请求
    @PostMapping("/register")
    public String register(User user, Model model) {
        int result = userService.register(user);
        if (result > 0) {
            return "redirect:/user/login";
        } else {
            model.addAttribute("error", "注册失败，用户名已存在");
            return "user/register";
        }
    }
    
    // 跳转到登录页面
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }
    
    // 处理登录请求
    @PostMapping("/login")
    public String login(String username, String password, HttpSession session, Model model) {
        User user = userService.login(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/home";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "user/login";
        }
    }
    
    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
    
    // 跳转到个人信息页面
    @GetMapping("/profile")
    public String profilePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 重新获取最新的用户信息
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);
        return "user/profile";
    }
    
    // 跳转到编辑个人信息页面
    @GetMapping("/edit")
    public String editPage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 重新获取最新的用户信息
        user = userService.getUserById(user.getId());
        model.addAttribute("user", user);
        return "user/edit";
    }
    
    // 处理更新个人信息请求
    @PostMapping("/update")
    public String update(User user, @RequestParam("birthDateStr") String birthDateStr, 
                         HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            return "redirect:/user/login";
        }
        
        try {
            // 设置用户ID
            user.setId(currentUser.getId());
            
            // 处理日期
            if (birthDateStr != null && !birthDateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date birthDate = sdf.parse(birthDateStr);
                user.setBirthDate(birthDate);
            }
            
            // 更新用户信息
            boolean success = userService.updateUser(user);
            if (success) {
                // 更新session中的用户信息
                session.setAttribute("user", userService.getUserById(user.getId()));
                return "redirect:/user/profile";
            } else {
                model.addAttribute("error", "更新失败");
                model.addAttribute("user", user);
                return "user/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "更新失败: " + e.getMessage());
            model.addAttribute("user", user);
            return "user/edit";
        }
    }
    
    // 跳转到修改密码页面
    // 在现有的UserController类中添加以下方法
    
    @GetMapping("/info")
    public String userInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        model.addAttribute("user", user);
        return "user/info";
    }
    
    @GetMapping("/password")
    public String passwordPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        return "user/password";
    }
    
    // 删除重复的方法定义
    @PostMapping("/password")
    public String changePassword(String oldPassword, String newPassword, 
                                HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        boolean success = userService.changePassword(user.getId(), oldPassword, newPassword);
        if (success) {
            // 密码修改成功，重新登录
            session.invalidate();
            return "redirect:/user/login?msg=password_changed";
        } else {
            model.addAttribute("error", "密码修改失败，请检查原密码是否正确");
            return "user/password";
        }
    }
}