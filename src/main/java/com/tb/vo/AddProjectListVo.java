package com.tb.vo;

public class AddProjectListVo {
    private String projectId;
    private String projectName;
    private String description;
    private String ownerName;
    private Integer needPeople;
    private Integer projectAgree;
    private Integer projectDisagree;
    private String projectResult;

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

    public Integer getNeedPeople() {
        return needPeople;
    }

    public void setNeedPeople(Integer needPeople) {
        this.needPeople = needPeople;
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

    public String getProjectResult() {
        return projectResult;
    }

    public void setProjectResult(String projectResult) {
        this.projectResult = projectResult;
    }
}
