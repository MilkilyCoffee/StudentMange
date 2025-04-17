package com.student.service.impl;

import com.student.dao.AwardDao;
import com.student.model.Award;
import com.student.service.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    @Autowired
    private AwardDao awardDao;

    @Override
    public List<Award> getAwardsByUserId(Integer userId) {
        try {
            return awardDao.getAwardsByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Award getAwardById(Integer id) {
        try {
            return awardDao.getAwardById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int addAward(Award award) {
        try {
            return awardDao.addAward(award);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean updateAward(Award award) {
        try {
            int result = awardDao.updateAward(award);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAward(Integer id, Integer userId) {
        try {
            int result = awardDao.deleteAward(id, userId);
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}