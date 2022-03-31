package com.tb.entity;

import java.util.Date;

public class Projecttoday {
    private Long id;

    private String projectId;

    private String pname;

    private String contactPersonName;

    private String ownerId;

    private Double time;

    private Double currency;

    private String category;

    private Date startTime;

    private Date endTime;

    private String address;

    private Integer needPeople;

    private String description;

    private Integer projectAgree;

    private Integer projectDisagree;

    private String contactPersonSex;

    private String contactPersonPhone;

    private String reviewerlist;

    private int flag;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

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

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getNeedPeople() {
        return needPeople;
    }

    public void setNeedPeople(Integer needPeople) {
        this.needPeople = needPeople;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProjectAgree() {
        return projectAgree;
    }

    public void setProjectAgree(Integer projectAgree) {
        this.projectAgree = projectAgree;
    }

    public Integer getProjectDisagree() {
        return projectDisagree;
    }

    public void setProjectDisagree(Integer projectDisagree) {
        this.projectDisagree = projectDisagree;
    }

    public String getContactPersonSex() {
        return contactPersonSex;
    }

    public void setContactPersonSex(String contactPersonSex) {
        this.contactPersonSex = contactPersonSex;
    }

    public String getContactPersonPhone() {
        return contactPersonPhone;
    }

    public void setContactPersonPhone(String contactPersonPhone) {
        this.contactPersonPhone = contactPersonPhone;
    }

    public String getReviewerlist() {
        return reviewerlist;
    }

    public void setReviewerlist(String reviewerlist) {
        this.reviewerlist = reviewerlist;
    }
}