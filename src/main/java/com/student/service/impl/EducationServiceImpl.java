package com.student.service.impl;

import com.student.dao.EducationDao;
import com.student.model.Education;
import com.student.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImpl implements EducationService {

    @Autowired
    private EducationDao educationDao;

    @Override
    public List<Education> getEducationsByUserId(Integer userId) {
        return educationDao.findByUserId(userId);
    }

    @Override
    public Education getEducationById(Integer id) {
        return educationDao.findById(id);
    }

    @Override
    public int addEducation(Education education) {
        return educationDao.addEducation(education);
    }

    @Override
    public boolean updateEducation(Education education) {
        int result = educationDao.updateEducation(education);
        return result > 0;
    }

    // 确保实现方法签名一致
    @Override
    public boolean deleteEducation(Integer id, Integer userId) {
        int result = educationDao.deleteEducation(id, userId);
        return result > 0;
    }
}