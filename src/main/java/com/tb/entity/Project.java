package com.tb.entity;

import java.util.Date;

public class Project {
    private Long id;

    private String projectId;

    private String pname;

    private String ownerName;

    private String ownerId;

    private Double time;

    private Double currency;

    private String category;

    private String state;

    private Date createTime;

    private Date startTime;

    private Date endTime;

    private String address;

    private Integer needPeople;

    private Integer nowPeople;

    private String description;

    private String recipient;

    private String remarkText;

    private String contactPersonName;

    private String contactPersonSex;

    private String contactPersonPhone;

    private Integer remarkFlag;

    public Integer getRemarkFlag() {
        return remarkFlag;
    }

    public void setRemarkFlag(Integer remarkFlag) {
        this.remarkFlag = remarkFlag;
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

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getNowPeople() {
        return nowPeople;
    }

    public void setNowPeople(Integer nowPeople) {
        this.nowPeople = nowPeople;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
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
}