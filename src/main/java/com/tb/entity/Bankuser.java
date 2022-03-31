package com.tb.entity;

import java.util.Date;

public class Bankuser {
    private Long id;

    private String appkey;

    private String username;

    private String phoneNumber;

    private String role;

    private String avatarUrl;

    private String userIDNumber;

    private String userRealName;

    private Date userBirthday;

    private Double userMoney;

    private Double userWorkTime;

    private String password;

    private String projectIdOut;

    private String projectIdIn;

    private String sex;

    private Integer level;

    private Integer projectNum;

    private Integer reviewerNum;

    private String voteId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getUserIDNumber() {
        return userIDNumber;
    }

    public void setUserIDNumber(String userIDNumber) {
        this.userIDNumber = userIDNumber;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public Double getUserMoney() {
        return userMoney;
    }

    public void setUserMoney(Double userMoney) {
        this.userMoney = userMoney;
    }

    public Double getUserWorkTime() {
        return userWorkTime;
    }

    public void setUserWorkTime(Double userWorkTime) {
        this.userWorkTime = userWorkTime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProjectIdOut() {
        return projectIdOut;
    }

    public void setProjectIdOut(String projectIdOut) {
        this.projectIdOut = projectIdOut;
    }

    public String getProjectIdIn() {
        return projectIdIn;
    }

    public void setProjectIdIn(String projectIdIn) {
        this.projectIdIn = projectIdIn;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getReviewerNum() {
        return reviewerNum;
    }

    public void setReviewerNum(Integer reviewerNum) {
        this.reviewerNum = reviewerNum;
    }

    public String getVoteId() {
        return voteId;
    }

    public void setVoteId(String voteId) {
        this.voteId = voteId;
    }
}