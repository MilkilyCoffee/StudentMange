package com.student.service;

import com.student.model.Education;
import java.util.List;

public interface EducationService {
    List<Education> getEducationsByUserId(Integer userId);
    Education getEducationById(Integer id);
    int addEducation(Education education);
    boolean updateEducation(Education education);
    // 确保方法签名如下
    boolean deleteEducation(Integer id, Integer userId);
}