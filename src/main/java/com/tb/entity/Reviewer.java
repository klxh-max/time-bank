package com.tb.entity;

public class Reviewer {
    private String appkey;

    private String userIDNumber;

    private String userRealName;

    private String desc;

    private String xuanyan;

    private String declaration;

    private Integer status;

    private Integer tasksNumber;

    private Integer reportTasks;

    private Integer reviewAddTasks;

    private Integer reviewDeclareTasks;

    private Integer votenum;

    public Integer getVotenum() {
        return votenum;
    }

    public void setVotenum(Integer votenum) {
        this.votenum = votenum;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getXuanyan() {
        return xuanyan;
    }

    public void setXuanyan(String xuanyan) {
        this.xuanyan = xuanyan;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTasksNumber() {
        return tasksNumber;
    }

    public void setTasksNumber(Integer tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public Integer getReportTasks() {
        return reportTasks;
    }

    public void setReportTasks(Integer reportTasks) {
        this.reportTasks = reportTasks;
    }

    public Integer getReviewAddTasks() {
        return reviewAddTasks;
    }

    public void setReviewAddTasks(Integer reviewAddTasks) {
        this.reviewAddTasks = reviewAddTasks;
    }

    public Integer getReviewDeclareTasks() {
        return reviewDeclareTasks;
    }

    public void setReviewDeclareTasks(Integer reviewDeclareTasks) {
        this.reviewDeclareTasks = reviewDeclareTasks;
    }
}