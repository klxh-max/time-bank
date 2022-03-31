package com.tb.service;

import com.tb.vo.ProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectService {
    int newproject(Map<String, Object> map1);

    List<ProjectVo> projectslist();

    Map<String,Object> projectbyid(String projectId);

    int comment(Map<String,Object> map1);

    Map<String,Object> myaddproject(String appkey);

    void updateProjectStatus();

}
