package com.tb.controller;

import com.tb.entity.Report;
import com.tb.service.ReviewerService;
import com.tb.vo.ReportResultVo;
import com.tb.vo.ReviewerInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ReviewerController {

    @Autowired
    private ReviewerService reviewerService;


    @RequestMapping(value = "api/applyreviewer",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> applyreviewer(@RequestBody Map<String,Object> map1){
        Map<String,Object> map = new HashMap<>();

        int result = reviewerService.applyReviewer(map1);

        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }else {
            map.put("code",500);
            map.put("msg","不可重复申请");
            map.put("data",null);
        }

        return map;
    }

    @RequestMapping(value = "api/vote",method = RequestMethod.POST)
    @ResponseBody
    public Map<String ,Object> vote(@RequestBody Map<String,Object> map1){
        Map<String,Object> map = new HashMap<>();

        int result = reviewerService.vote(map1);

        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }else {
            map.put("code",500);
            map.put("msg","投票失败");
            map.put("data",null);
        }

        return map;
    }
    
    @RequestMapping(value = "api/candidateinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> candidateInfo(String appkey,String id){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = reviewerService.candidateInfo(appkey,id);

        map.put("code",0);
        map.put("msg","");
        map.put("data",map1);

        return map;
    }

    @RequestMapping(value = "api/reviewerlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> reviewerList(String appkey){
        Map<String,Object> map = new HashMap<>();

        List<ReviewerInfoVo> list = reviewerService.reviewerList(appkey);
        map.put("code",0);
        map.put("msg","");
        map.put("data",list);

        return map;
    }


    @RequestMapping(value = "api/reportreviewlist",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> reportReviewlist(String appkey){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = new HashMap<>();

        List<Report> list = reviewerService.reportReviewlist("appkey");
        int ingNum = 0;
        int overNum = 0;
        int allNum = 0;


        String agreeReviewIds;
        String disagreeReviewIds;
        for(Report r:list){

            allNum++;
            agreeReviewIds = r.getAgreeReviewIds();
            disagreeReviewIds = r.getDisagreeReviewIds();

            if (agreeReviewIds != null && !"".equals(agreeReviewIds)){
                String[] agreeReviewIdsList = agreeReviewIds.split(",");
                for(String a:agreeReviewIdsList){
                    if (appkey.equals(a)){
                        overNum++;
                        r.setTips("over");
                        break;
                    }
                }
            }
            else if(!"".equals(disagreeReviewIds) && disagreeReviewIds != null){
                String[] disagreeReviewIdsList = disagreeReviewIds.split(",");
                for(String d:disagreeReviewIdsList){
                    if (appkey.equals(d)){
                        overNum++;
                        r.setTips("back");
                        break;
                    }
                }
            }

        }

        map1.put("ingNum",allNum-overNum);
        map1.put("overNum",overNum);
        map1.put("list",list);

        map.put("code",0);
        map.put("msg","");
        map.put("data",map1);

        return map;
    }

    @RequestMapping(value = "api/report/result",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> reportResult(@RequestBody ReportResultVo r1){
        Map<String,Object> map = new HashMap<>();

        int result = reviewerService.reportResult(r1);

        if (result==1){
            map.put("code",0);
            map.put("msg","");
            map.put("data",null);
        }else {
            map.put("code",500);
            map.put("msg","请求失败");
            map.put("data",null);
        }

        return map;
    }


    @RequestMapping(value = "api/getreviewerinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getreviewerInfo(String appkey){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> map1 = reviewerService.getreviewerInfo(appkey);

        map.put("code",0);
        map.put("msg","");
        map.put("data",map1);
        return map;
    }

    @Scheduled(cron="0 59 23 * * ?")
    public void updateReviewByday(){
        System.out.println("hello");
        reviewerService.updateReviewByday();
        System.out.println("每天凌晨更换审核人la！");
    }

    @RequestMapping(value = "api/myaddproject",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> myaddproject(String appkey){

        Map<String,Object> m = new HashMap<>();
        Map<String,Object> map = reviewerService.myaddproject(appkey);

        m.put("code",0);
        m.put("msg","");
        m.put("data",map);

        return m;
    }
}
