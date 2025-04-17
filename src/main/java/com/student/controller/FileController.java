package com.student.controller;

import com.student.model.User;
import com.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    private UserService userService;

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file, 
                                         HttpServletRequest request, 
                                         HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        
        // 检查用户是否登录
        User user = (User) session.getAttribute("user");
        if (user == null) {
            result.put("success", false);
            result.put("message", "请先登录");
            return result;
        }
        
        if (file.isEmpty()) {
            result.put("success", false);
            result.put("message", "请选择文件");
            return result;
        }
        
        try {
            // 获取文件名
            String originalFilename = file.getOriginalFilename();
            // 获取文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成新文件名
            String newFilename = UUID.randomUUID().toString() + suffix;
            
            // 确定文件存储路径（相对于Web应用根目录）
            String uploadDir = "/resources/uploads/";
            String realPath = request.getServletContext().getRealPath(uploadDir);
            
            // 创建目录
            File dir = new File(realPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            
            // 保存文件
            File destFile = new File(realPath + newFilename);
            file.transferTo(destFile);
            
            // 更新用户头像信息
            String avatarPath = uploadDir + newFilename;
            user.setAvatar(avatarPath);
            userService.updateUserAvatar(user.getId(), avatarPath);
            
            // 更新session中的用户信息
            session.setAttribute("user", user);
            
            result.put("success", true);
            result.put("message", "上传成功");
            result.put("avatarPath", avatarPath);
            
        } catch (IOException e) {
            e.printStackTrace();
            result.put("success", false);
            result.put("message", "上传失败: " + e.getMessage());
        }
        
        return result;
    }
}