package com.tb.entity;

import java.util.Date;

public class Report {
    private String reportId;

    private String category;

    private String ownerId;

    private String reportName;

    private String desc;

    private Date time;

    private String ownerName;

    private String phoneNumber;

    private Integer agree;

    private Integer disagree;

    private String tips;

    private String agreeReviewIds;

    private String disagreeReviewIds;

    public String getAgreeReviewIds() {
        return agreeReviewIds;
    }

    public void setAgreeReviewIds(String agreeReviewIds) {
        this.agreeReviewIds = agreeReviewIds;
    }

    public String getDisagreeReviewIds() {
        return disagreeReviewIds;
    }

    public void setDisagreeReviewIds(String disagreeReviewIds) {
        this.disagreeReviewIds = disagreeReviewIds;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getDisagree() {
        return disagree;
    }

    public void setDisagree(Integer disagree) {
        this.disagree = disagree;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}