package com.tb.vo;

import java.util.Date;

public class DeclareProjectListVo {
    private String projectId;
    private String projectName;
    private String description;
    private String ownerName;
    private Date overTime;
    private Integer remarkAgree;
    private Integer remarkDisagree;
    private String state;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Integer getRemarkAgree() {
        return remarkAgree;
    }

    public void setRemarkAgree(Integer remarkAgree) {
        this.remarkAgree = remarkAgree;
    }

    public Integer getRemarkDisagree() {
        return remarkDisagree;
    }

    public void setRemarkDisagree(Integer remarkDisagree) {
        this.remarkDisagree = remarkDisagree;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
