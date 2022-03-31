package com.tb.vo;

import com.tb.entity.Projectrelated;

import java.util.Date;
import java.util.List;

public class FinishProVo {

    private String projectId;
    private String pname;
    private String description;
    private int needPeople;
    private int nowPeople;
    private String category;
    private double currency;
    private double time;
    private String address;
    private String contactPersonName;
    private Date createTime;
    private Date startTime;
    private Date endTime;
    private List<Projectrelated> userList;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNeedPeople() {
        return needPeople;
    }

    public void setNeedPeople(int needPeople) {
        this.needPeople = needPeople;
    }

    public int getNowPeople() {
        return nowPeople;
    }

    public void setNowPeople(int nowPeople) {
        this.nowPeople = nowPeople;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getCurrency() {
        return currency;
    }

    public void setCurrency(double currency) {
        this.currency = currency;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
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

    public List<Projectrelated> getUserList() {
        return userList;
    }

    public void setUserList(List<Projectrelated> userList) {
        this.userList = userList;
    }
}
