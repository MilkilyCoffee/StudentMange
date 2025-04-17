package com.student.model;

import java.util.Date;

public class Award {
    private Integer id;
    private Integer userId;
    private String awardName;
    private String awardLevel;
    private String issuer;
    private Date awardDate;
    private String description;
    
    // 构造函数
    public Award() {}
    
    // getter和setter方法
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getAwardName() {
        return awardName;
    }
    
    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
    
    public String getAwardLevel() {
        return awardLevel;
    }
    
    public void setAwardLevel(String awardLevel) {
        this.awardLevel = awardLevel;
    }
    
    public String getIssuer() {
        return issuer;
    }
    
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }
    
    public Date getAwardDate() {
        return awardDate;
    }
    
    public void setAwardDate(Date awardDate) {
        this.awardDate = awardDate;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
}