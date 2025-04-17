package com.student.controller;

import com.student.model.Award;
import com.student.model.User;
import com.student.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/award")
public class AwardController {

    @Autowired
    private AwardService awardService;

    @GetMapping("/list")
    public String list(HttpSession session, Model model) {
        System.out.println("访问获奖情况列表页面");
        User user = (User) session.getAttribute("user");
        if (user == null) {
            System.out.println("用户未登录，重定向到登录页面");
            return "redirect:/user/login";
        }
        
        try {
            System.out.println("获取用户ID: " + user.getId() + "的获奖信息");
            List<Award> awards = awardService.getAwardsByUserId(user.getId());
            model.addAttribute("awards", awards != null ? awards : new ArrayList<>());
            return "award/list";
        } catch (Exception e) {
            System.out.println("获取获奖信息失败: " + e.getMessage());
            e.printStackTrace();
            model.addAttribute("error", "获取获奖信息失败: " + e.getMessage());
            model.addAttribute("awards", new ArrayList<>());
            return "award/list";
        }
    }
    
    @GetMapping("/add")
    public String addPage(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "award/add";
    }
    
    @PostMapping("/add")
    public String add(Award award, 
                     @RequestParam(value = "awardDateStr", required = false) String awardDateStr,
                     HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            // 设置用户ID
            award.setUserId(user.getId());
            
            // 处理日期
            if (awardDateStr != null && !awardDateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date awardDate = sdf.parse(awardDateStr);
                award.setAwardDate(awardDate);
            }
            
            // 添加获奖信息
            int result = awardService.addAward(award);
            if (result > 0) {
                return "redirect:/award/list";
            } else {
                model.addAttribute("error", "添加失败");
                return "award/add";
            }
        } catch (Exception e) {
            model.addAttribute("error", "添加失败: " + e.getMessage());
            return "award/add";
        }
    }
    
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Integer id, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            Award award = awardService.getAwardById(id);
            if (award == null || !award.getUserId().equals(user.getId())) {
                return "redirect:/award/list";
            }
            
            model.addAttribute("award", award);
            return "award/edit";
        } catch (Exception e) {
            model.addAttribute("error", "获取获奖信息失败: " + e.getMessage());
            return "redirect:/award/list";
        }
    }
    
    @PostMapping("/update")
    public String update(Award award, 
                        @RequestParam(value = "awardDateStr", required = false) String awardDateStr,
                        HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            // 验证权限
            Award existingAward = awardService.getAwardById(award.getId());
            if (existingAward == null || !existingAward.getUserId().equals(user.getId())) {
                return "redirect:/award/list";
            }
            
            // 设置用户ID
            award.setUserId(user.getId());
            
            // 处理日期
            if (awardDateStr != null && !awardDateStr.isEmpty()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date awardDate = sdf.parse(awardDateStr);
                award.setAwardDate(awardDate);
            }
            
            // 更新获奖信息
            boolean success = awardService.updateAward(award);
            if (success) {
                return "redirect:/award/list";
            } else {
                model.addAttribute("error", "更新失败");
                model.addAttribute("award", award);
                return "award/edit";
            }
        } catch (Exception e) {
            model.addAttribute("error", "更新失败: " + e.getMessage());
            model.addAttribute("award", award);
            return "award/edit";
        }
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        
        try {
            awardService.deleteAward(id, user.getId());
            return "redirect:/award/list";
        } catch (Exception e) {
            return "redirect:/award/list";
        }
    }
}