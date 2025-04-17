package com.student.service;

import com.student.model.Award;
import java.util.List;

public interface AwardService {
    List<Award> getAwardsByUserId(Integer userId);
    Award getAwardById(Integer id);
    int addAward(Award award);
    boolean updateAward(Award award);
    boolean deleteAward(Integer id, Integer userId);
}