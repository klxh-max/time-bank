package com.tb.vo;

public class ReviewerInfoVo {

    private  String id;
    private String avatar;
    private String declaration;
    private String name;
    private int tasksNumber;
    private int reportTasks;
    private int reviewTasks;
    private int reviewAddTasks;
    private int reviewDeclareTasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDeclaration() {
        return declaration;
    }

    public void setDeclaration(String declaration) {
        this.declaration = declaration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTasksNumber() {
        return tasksNumber;
    }

    public void setTasksNumber(int tasksNumber) {
        this.tasksNumber = tasksNumber;
    }

    public int getReportTasks() {
        return reportTasks;
    }

    public void setReportTasks(int reportTasks) {
        this.reportTasks = reportTasks;
    }

    public int getReviewTasks() {
        return reviewTasks;
    }

    public void setReviewTasks(int reviewTasks) {
        this.reviewTasks = reviewTasks;
    }

    public int getReviewAddTasks() {
        return reviewAddTasks;
    }

    public void setReviewAddTasks(int reviewAddTasks) {
        this.reviewAddTasks = reviewAddTasks;
    }

    public int getReviewDeclareTasks() {
        return reviewDeclareTasks;
    }

    public void setReviewDeclareTasks(int reviewDeclareTasks) {
        this.reviewDeclareTasks = reviewDeclareTasks;
    }
}
