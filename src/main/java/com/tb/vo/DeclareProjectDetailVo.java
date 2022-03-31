package com.tb.vo;

import java.util.Date;

public class DeclareProjectDetailVo {

    private Integer remarkAgree;// 项目申报同意人数
    private Integer remarkDisagree;// 项目申报拒绝人数
    private String projectName;// 项目名称
    private String projectId;// 项目id
    private Date endTime;// 项目结束时间
    private Integer needPeople;// 项目所需人数
    private String category;// 项目分类
    private Double currency;// 项目单人支付时间币
    private Double time;// 项目需要的工作时间
    private String address;// 项目地址
    private String ownerName;// 项目负责人姓名
    private String contactPersonName;// 项目联系人姓名
    private String remarkText;// 备注

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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getNeedPeople() {
        return needPeople;
    }

    public void setNeedPeople(Integer needPeople) {
        this.needPeople = needPeople;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getRemarkText() {
        return remarkText;
    }

    public void setRemarkText(String remarkText) {
        this.remarkText = remarkText;
    }
}
