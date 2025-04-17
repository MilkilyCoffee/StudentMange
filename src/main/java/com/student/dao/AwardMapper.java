package com.student.dao;

import com.student.model.Award;
import java.util.List;

public interface AwardMapper {
    List<Award> findByUserId(Integer userId);
    Award findById(Integer id);
    void insert(Award award);
    void update(Award award);
    void delete(Integer id);
}