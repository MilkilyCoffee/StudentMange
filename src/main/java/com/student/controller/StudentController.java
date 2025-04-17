package com.student.controller;

import com.student.model.Education;
import com.student.model.User;
import com.student.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/info")
    public String studentInfo(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            List<Education> educations = educationService.getEducationsByUserId(user.getId());
            model.addAttribute("educations", educations);
            return "student/info";
        } catch (Exception e) {
            model.addAttribute("error", "获取学历信息失败: " + e.getMessage());
            return "error";
        }
    }
    
    @GetMapping("/add")
    public String addPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "student/add";
    }
    
    @PostMapping("/add")
    public String add(Education education, 
                     @RequestParam(value = "startDateStr", required = false) String startDateStr,
                     @RequestParam(value = "endDateStr", required = false) String endDateStr,
                     HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            // 设置用户ID
            education.setUserId(user.getId());
            
            // 处理日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                Date startDate = sdf.parse(startDateStr);
                education.setStartDate(startDate);
            }
            
            if (endDateStr != null && !endDateStr.isEmpty()) {
                Date endDate = sdf.parse(endDateStr);
                education.setEndDate(endDate);
            }
            
            // 添加学历信息
            int result = educationService.addEducation(education);
            if (result > 0) {
                return "redirect:/student/info";
            } else {
                model.addAttribute("error", "添加失败");
                return "student/add";
            }
        } catch (Exception e) {
            model.addAttribute("error", "添加失败: " + e.getMessage());
            return "student/add";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            Education education = educationService.getEducationById(id);
            if (education == null || !education.getUserId().equals(user.getId())) {
                return "redirect:/student/info";
            }
            
            model.addAttribute("education", education);
            return "student/edit";
        } catch (Exception e) {
            model.addAttribute("error", "获取学历信息失败: " + e.getMessage());
            return "redirect:/student/info";
        }
    }
    
    @PostMapping("/update")
    public String update(Education education, 
                        @RequestParam(value = "startDateStr", required = false) String startDateStr,
                        @RequestParam(value = "endDateStr", required = false) String endDateStr,
                        HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            // 验证权限
            Education existingEducation = educationService.getEducationById(education.getId());
            if (existingEducation == null || !existingEducation.getUserId().equals(user.getId())) {
                return "redirect:/student/info";
            }
            
            // 设置用户ID
            education.setUserId(user.getId());
            
            // 处理日期
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                Date startDate = sdf.parse(startDateStr);
                education.setStartDate(startDate);
            }
            
            if (endDateStr != null && !endDateStr.isEmpty()) {
                Date endDate = sdf.parse(endDateStr);
                education.setEndDate(endDate);
            }
            
            // 更新学历信息
            boolean success = educationService.updateEducation(education);
            if (success) {
                return "redirect:/student/info";
            } else {
                model.addAttribute("error", "更新失败");
                model.addAttribute("education", education);
                return "student/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "更新失败: " + e.getMessage());
            model.addAttribute("education", education);
            return "student/edit";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            educationService.deleteEducation(id, user.getId());
            return "redirect:/student/info";
        } catch (Exception e) {
            return "redirect:/student/info";
        }
    }
}