package com.student.dao;

import com.student.model.Award;
import java.util.List;

public interface AwardDao {
    List<Award> getAwardsByUserId(Integer userId);
    Award getAwardById(Integer id);
    int addAward(Award award);
    int updateAward(Award award);
    int deleteAward(Integer id, Integer userId);
}