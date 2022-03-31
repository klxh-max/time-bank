package com.tb.service;

import com.tb.entity.Projecttoday;
import com.tb.vo.AddProjectListVo;
import com.tb.vo.DeclareProjectDetailVo;
import com.tb.vo.DeclareProjectListVo;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface RemarkTodayService {

    int declareproject(Map<String, Object> map1);

    List<DeclareProjectListVo> declareprojectlist(String appkey);

    Map<String,Object> declareprojectdetail(String projectId);

    int result(String projectId,String appkey,Boolean result);
}