package com.student.controller;

import com.student.model.Course;
import com.student.model.User;
import com.student.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/info")
    public String courseInfo(HttpSession session, Model model) {
        System.out.println("访问课程信息页面");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("用户未登录，重定向到登录页面");
            return "redirect:/user/login";
        }
        
        try {
            System.out.println("获取用户ID: " + user.getId() + "的课程信息");
            List<Course> courses = courseService.getCoursesByUserId(user.getId());
            model.addAttribute("courses", courses);
            return "course/info";
        } catch (Exception e) {
            System.out.println("获取课程信息失败: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "获取课程信息失败: " + e.getMessage());
            // 如果出错，至少返回一个空列表，避免JSP页面出错
            model.addAttribute("courses", new ArrayList<Course>());
            return "course/info";
        }
    }
    
    @GetMapping("/add")
    public String addPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "course/add";
    }
    
    @PostMapping("/add")
    public String add(Course course, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            course.setUserId(user.getId());
            int result = courseService.addCourse(course);
            if (result > 0) {
                return "redirect:/course/info";
            } else {
                model.addAttribute("error", "添加失败");
                return "course/add";
            }
        } catch (Exception e) {
            model.addAttribute("error", "添加失败: " + e.getMessage());
            return "course/add";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            Course course = courseService.getCourseById(id);
            if (course == null || !course.getUserId().equals(user.getId())) {
                return "redirect:/course/info";
            }
            
            model.addAttribute("course", course);
            return "course/edit";
        } catch (Exception e) {
            model.addAttribute("error", "获取课程信息失败: " + e.getMessage());
            return "redirect:/course/info";
        }
    }
    
    @PostMapping("/update")
    public String update(Course course, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            Course existingCourse = courseService.getCourseById(course.getId());
            if (existingCourse == null || !existingCourse.getUserId().equals(user.getId())) {
                return "redirect:/course/info";
            }
            
            course.setUserId(user.getId());
            boolean success = courseService.updateCourse(course);
            if (success) {
                return "redirect:/course/info";
            } else {
                model.addAttribute("error", "更新失败");
                model.addAttribute("course", course);
                return "course/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "更新失败: " + e.getMessage());
            model.addAttribute("course", course);
            return "course/edit";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            courseService.deleteCourse(id, user.getId());
            return "redirect:/course/info";
        } catch (Exception e) {
            return "redirect:/course/info";
        }
    }
}