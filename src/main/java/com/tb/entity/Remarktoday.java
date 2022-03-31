package com.tb.entity;

public class Remarktoday {
    private Long id;

    private String projectId;

    private String projectName;

    private String ownerId;

    private String description;

    private String reviewerlist;

    private Integer remarkAgree;

    private Integer remarkDisagree;

    private String remarkText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }

    public String getReviewerlist() {
        return reviewerlist;
    }

    public void setReviewerlist(String reviewerlist) {
        this.reviewerlist = reviewerlist;
    }
}