package com.tb.vo;

import java.util.Date;

public class VolunteerVo {
    private String appkey;
    private String username;
    private Integer level;
    private String projectId;
    private String projectName;
    private Double userWorkTime;
    private Integer projectNum;
    private Date acceptTime;

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getUserWorkTime() {
        return userWorkTime;
    }

    public void setUserWorkTime(Double userWorkTime) {
        this.userWorkTime = userWorkTime;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }
}
