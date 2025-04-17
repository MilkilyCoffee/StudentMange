package com.student.controller;

import com.student.model.Education; // 确保正确导入Education类
import com.student.model.User;
import com.student.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private EducationService educationService;

    @GetMapping({"/", "/home"})
    public String home(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 移除对Education的引用，简化首页逻辑
        model.addAttribute("username", user.getUsername());
        
        return "home";
    }
}