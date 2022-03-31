package com.tb.controller;

import com.tb.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;


    @RequestMapping(value = "api/reportproject",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> reportProject(@RequestBody Map<String, Object> map1){

        Map<String,Object> map=new HashMap();
        int result = reportService.addReportProject(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }
        else if (result == 2){
            map.put("code",500);
            map.put("msg","项目不存在");
            map.put("data",null);
        }
        else if (result == 3){
            map.put("code",500);
            map.put("msg","用户不存在");
            map.put("data",null);
        }
        else if (result == 4){
            map.put("code",500);
            map.put("msg","不可重复举报");
            map.put("data",null);
        }
        else {
            map.put("code",500);
            map.put("msg","举报项目失败");
            map.put("data",null);
        }
        return map;
    }

    @RequestMapping(value = "api/reportoriginator",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> reportOriginator(@RequestBody Map<String,Object> map1){



        Map<String,Object> map=new HashMap();
        int result = reportService.addReportOriginator(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }
        else if (result == 2){
            map.put("code",500);
            map.put("msg","项目不存在");
            map.put("data",null);
        }
        else if (result == 3){
            map.put("code",500);
            map.put("msg","用户不存在");
            map.put("data",null);
        }
        else if (result == 4){
            map.put("code",500);
            map.put("msg","不可重复举报");
            map.put("data",null);
        }
        else {
            map.put("code",500);
            map.put("msg","举报发起人失败");
            map.put("data",null);
        }
        return map;
    }

    @RequestMapping(value = "api/reportreviewer",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> reportReviewer(@RequestBody Map<String,Object> map1){
        Map<String,Object> map=new HashMap();
        int result = reportService.addReportReviewer(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }
        else if (result == 2){
            map.put("code",500);
            map.put("msg","项目不存在");
            map.put("data",null);
        }
        else if (result == 3){
            map.put("code",500);
            map.put("msg","用户不存在");
            map.put("data",null);
        }
        else if (result == 4){
            map.put("code",500);
            map.put("msg","不可重复举报");
            map.put("data",null);
        }
        else {
            map.put("code",500);
            map.put("msg","举报发起人失败");
            map.put("data",null);
        }
        return map;
    }

}

