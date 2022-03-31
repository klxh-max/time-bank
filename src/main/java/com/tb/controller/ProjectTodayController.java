package com.tb.controller;

import com.tb.entity.Projecttoday;
import com.tb.service.ProjectTodayService;
import com.tb.vo.AddProjectListVo;
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
public class ProjectTodayController {

    @Autowired
    private ProjectTodayService projectTodayService;

    @RequestMapping("api/addprojectlist")
    @ResponseBody
    public Map<String,Object> addprojectlist(String appkey){
        Map<String,Object> map=new HashMap();
        Map<String,Object> map1=new HashMap<>();
        map1.put("list",projectTodayService.addprojectlist(appkey));
        List<AddProjectListVo> voList=projectTodayService.addprojectlist(appkey);
        int ingProject=0,overProject=0;//等待审核的项目,审核完成的项目
        for (AddProjectListVo vo:voList){
            if("over".equals(vo.getProjectResult())){
                overProject++;
            }else {
                ingProject++;
            }
        }
        map1.put("ingProject",ingProject);
        map1.put("overProject",overProject);
        map.put("data",map1);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("api/addprojectdetail")
    @ResponseBody
    public Map<String,Object> addprojectdetail(@Param("projectId") String projectId){
        Projecttoday projecttoday=projectTodayService.addprojectdetail(projectId);
        Map<String,Object> map=new HashMap();
        map.put("data",projecttoday);
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("api/addproject/result")
    @ResponseBody
    public Map<String,Object> result(@RequestBody Map<String, Object> map1){
        String projectId= (String) map1.get("projectId");
        String appkey = (String) map1.get("appkey");
        Boolean result=(Boolean) map1.get("result");
        int sum= projectTodayService.result(projectId,appkey,result);
        Map<String,Object> map=new HashMap();
        if(sum==3){
            map.put("data",null);
            map.put("code",0);
            map.put("msg","");
        }else {
            map.put("code",500);
            map.put("msg","服务器开小差了~~~");
        }
        return map;
    }

    @RequestMapping("api/projectinfo")
    @ResponseBody
    public Map<String,Object> projectinfo(@RequestBody Map<String, Object> map1){
        Map<String,Object> map=new HashMap();
        if(projectTodayService.projectinfo(map1)==1){
            map.put("data",null);
            map.put("code",0);
            map.put("msg","");
        }else {
            map.put("code",500);
            map.put("msg","服务器开小差了~~~");
        }
        return map;
    }

}
