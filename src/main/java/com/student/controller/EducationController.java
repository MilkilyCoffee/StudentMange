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
@RequestMapping("/education")
public class EducationController {

    @Autowired
    private EducationService educationService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        List<Education> educations = educationService.getEducationsByUserId(user.getId());
        model.addAttribute("educations", educations);
        return "education/list";
    }
    
    @GetMapping("/add")
    public String addPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "education/add";
    }
    
    @PostMapping("/add")
    public String add(Education education, 
                     @RequestParam("startDateStr") String startDateStr,
                     @RequestParam("endDateStr") String endDateStr,
                     HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            education.setUserId(user.getId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                Date startDate = sdf.parse(startDateStr);
                education.setStartDate(startDate);
            }
            
            if (endDateStr != null && !endDateStr.isEmpty()) {
                Date endDate = sdf.parse(endDateStr);
                education.setEndDate(endDate);
            }
            
            int result = educationService.addEducation(education);
            if (result > 0) {
                return "redirect:/education/list";
            } else {
                model.addAttribute("error", "添加失败");
                return "education/add";
            }
        } catch (Exception e) {
            model.addAttribute("error", "添加失败: " + e.getMessage());
            return "education/add";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        Education education = educationService.getEducationById(id);
        if (education == null || !education.getUserId().equals(user.getId())) {
            return "redirect:/education/list";
        }
        
        model.addAttribute("education", education);
        return "education/edit";
    }
    
    @PostMapping("/update")
    public String update(Education education, 
                        @RequestParam("startDateStr") String startDateStr,
                        @RequestParam("endDateStr") String endDateStr,
                        HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            Education existingEducation = educationService.getEducationById(education.getId());
            if (existingEducation == null || !existingEducation.getUserId().equals(user.getId())) {
                return "redirect:/education/list";
            }
            
            education.setUserId(user.getId());
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            if (startDateStr != null && !startDateStr.isEmpty()) {
                Date startDate = sdf.parse(startDateStr);
                education.setStartDate(startDate);
            }
            
            if (endDateStr != null && !endDateStr.isEmpty()) {
                Date endDate = sdf.parse(endDateStr);
                education.setEndDate(endDate);
            }
            
            boolean success = educationService.updateEducation(education);
            if (success) {
                return "redirect:/education/list";
            } else {
                model.addAttribute("error", "更新失败");
                model.addAttribute("education", education);
                return "education/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "更新失败: " + e.getMessage());
            model.addAttribute("education", education);
            return "education/edit";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        // 这里确保传递两个Integer类型参数
        educationService.deleteEducation(id, user.getId());
        return "redirect:/education/list";
    }
}