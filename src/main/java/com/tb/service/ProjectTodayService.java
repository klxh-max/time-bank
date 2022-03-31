package com.tb.service;

import com.tb.entity.Projecttoday;
import com.tb.vo.AddProjectListVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface ProjectTodayService {

    List<AddProjectListVo> addprojectlist(String appkey);

    Projecttoday addprojectdetail(String projectId);

    int result(String projectId,String appkey,Boolean result);

    int projectinfo(Map<String, Object> map);
}
