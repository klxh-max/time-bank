package com.tb.vo;

public class CandidateVo {

    private String name;
    private String id;
    private String avatar;
    private String xuanyan;
    private Integer votes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getXuanyan() {
        return xuanyan;
    }

    public void setXuanyan(String xuanyan) {
        this.xuanyan = xuanyan;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
