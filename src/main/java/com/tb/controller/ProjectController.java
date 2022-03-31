package com.tb.controller;

import com.tb.service.ProjectService;
import com.tb.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("api/newproject")
    @ResponseBody
    public Map<String,Object> newproject(@RequestBody Map<String, Object> map1){
        Map<String,Object> map=new HashMap();
        int result= projectService.newproject(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }else {
            map.put("code",500);
            map.put("msg","项目发起失败");
            map.put("data",null);
        }
        return map;
    }

    @RequestMapping("api/projectslist")
    @ResponseBody
    public Map<String,Object> projectslist(){
        Map<String,Object> map=new HashMap();
        map.put("data",projectService.projectslist());
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping("api/projectbyid")
    @ResponseBody
    public Map<String,Object> projectbyid(@Param("projectId") String projectId){
        Map<String,Object> map=new HashMap();
        map.put("data",projectService.projectbyid(projectId));
        map.put("code",0);
        map.put("msg","");
        return map;
    }

    @RequestMapping(value = "api/comment",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> comment(@RequestBody Map<String,Object> map1){
        Map<String ,Object> map = new HashMap();

        int result= projectService.comment(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }else {
            map.put("code",500);
            map.put("msg","评论上传失败");
            map.put("data",null);
        }

        return map;
    }





}
