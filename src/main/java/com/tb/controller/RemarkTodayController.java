package com.tb.controller;

import com.tb.entity.Projecttoday;
import com.tb.service.RemarkTodayService;
import com.tb.vo.AddProjectListVo;
import com.tb.vo.DeclareProjectListVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RemarkTodayController {

    @Autowired
    private RemarkTodayService remarkTodayService;

    @RequestMapping("api/declareproject")
    @ResponseBody
    public Map<String, Object> declareproject(@RequestBody Map<String, Object> map1) {
        Map<String, Object> map = new HashMap();
        if (remarkTodayService.declareproject(map1) == 1) {
            map.put("data", null);
            map.put("code", 0);
            map.put("msg", "");
        } else {
            map.put("code", 500);
            map.put("msg", "服务器开小差了~~~");
        }
        return map;
    }

    @RequestMapping("api/declareprojectlist")
    @ResponseBody
    public Map<String, Object> declareprojectlist(String appkey) {
        Map<String, Object> map = new HashMap();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("list", remarkTodayService.declareprojectlist(appkey));
        List<DeclareProjectListVo> voList = remarkTodayService.declareprojectlist(appkey);
        int ingProject = 0, overProject = 0;//等待审核的项目,审核完成的项目
        for (DeclareProjectListVo vo : voList) {
            if ("over".equals(vo.getState())) {
                overProject++;
            } else {
                ingProject++;
            }
        }
        map1.put("ingProject", ingProject);
        map1.put("overProject", overProject);
        map.put("data", map1);
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @RequestMapping("api/declareprojectdetail")
    @ResponseBody
    public Map<String, Object> declareprojectdetail(@Param("projectId") String projectId) {
        Map<String, Object> map = new HashMap();
        map.put("data", remarkTodayService.declareprojectdetail(projectId));
        map.put("code", 0);
        map.put("msg", "");
        return map;
    }

    @RequestMapping("api/declareproject/result")
    @ResponseBody
    public Map<String, Object> result(@RequestBody Map<String, Object> map1) {
        String projectId = (String) map1.get("projectId");
        String appkey = (String) map1.get("appkey");
        Boolean result = (Boolean) map1.get("result");
        int sum = remarkTodayService.result(projectId, appkey, result);
        Map<String, Object> map = new HashMap();
        if (sum == 3) {
            map.put("data", null);
            map.put("code", 0);
            map.put("msg", "");
        } else {
            map.put("code", 500);
            map.put("msg", "服务器开小差了~~~");
        }
        return map;

    }
}
