package com.tb.controller;

import com.tb.entity.Project;
import com.tb.entity.Projectrelated;
import com.tb.service.UserService;
import com.tb.vo.MyProjectVo;
import com.tb.vo.UserVo;
import com.tb.vo.VolunteerApplyVo;
import com.tb.vo.VolunteerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "api/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String, Object> map1){
        String phoneNumber=(String) map1.get("phoneNumber");
        String password=(String) map1.get("password");
        UserVo userVo= userService.login(phoneNumber, password);
        Map<String,Object> map=new HashMap();
        if ("".equals(userVo.getAppkey())||userVo.getAppkey()==null){
            map.put("code",406);
            map.put("msg","用户名或密码错误");
        }else {
            map.put("code",0);
            map.put("msg","登陆成功");
            map.put("data",userVo);
        }
        return map;
    }


    @RequestMapping(value = "api/acceptproject",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object>acceptProject(@RequestBody Map<String,Object> map1){
        Map<String,Object> map = new HashMap();

        int result = userService.acceptproject(map1);
        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }
        else if(result == 2){
            map.put("code",500);
            map.put("msg","不可重复提交申请");
            map.put("data",null);
        }
        else {
            map.put("code",500);
            map.put("msg","承接项目失败");
            map.put("data",null);
        }

        return map;
    }

    @RequestMapping(value = "api/myproject",method = RequestMethod.GET)
    @ResponseBody
    public Map<String ,Object> myProject(String appkey){
        Map<String,Object> map = new HashMap();
        Map<String,Object> map1 = new HashMap();

        List<MyProjectVo> doing = new ArrayList<>();
        List<MyProjectVo> waitForComment = new ArrayList<>();
        List<MyProjectVo> finish = new ArrayList<>();

        List<MyProjectVo> projectrelatedList = userService.queryMyProject(appkey);

        for(MyProjectVo p:projectrelatedList){

            if("doing".equals(p.getUserState())){
                doing.add(p);
            }
            else if("waitForComment".equals(p.getUserState())){
                waitForComment.add(p);
            }
            else if("finish".equals(p.getUserState())){
                finish.add(p);
            }
        }

        map1.put("doing",doing);
        map1.put("waitForComment",waitForComment);
        map1.put("finish",finish);

        map.put("code",0);
        map.put("msg","");
        map.put("data",map1);


        return map;
    }

    @RequestMapping(value = "api/volunteerlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> volunteerlist(String appkey){
        Map<String ,Object> map = new HashMap<>();
        List<VolunteerVo> list = userService.queryVolunteerlistByAppkey(appkey);
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);
        return map;
    }

    @RequestMapping(value = "api/volunteerapply",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> volunteerapply(@RequestBody VolunteerApplyVo v){
        Map<String ,Object> map = new HashMap<>();

        int result = userService.volunteerApply(v);

        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }
        else {
            map.put("code",500);
            map.put("msg","请求失败");
            map.put("data",null);
        }

        return map;
    }

    @RequestMapping(value = "api/getuserinfo")
    @ResponseBody
    public Map<String,Object> getuserinfo(String appkey){
        Map<String ,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("data",userService.getuserinfo(appkey));
        return map;
    }
}
