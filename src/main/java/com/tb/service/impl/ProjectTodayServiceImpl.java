package com.tb.service.impl;

import com.tb.entity.Project;
import com.tb.entity.Projecttoday;
import com.tb.mapper.BankuserMapper;
import com.tb.mapper.ProjectMapper;
import com.tb.mapper.ProjecttodayMapper;
import com.tb.mapper.ReviewerMapper;
import com.tb.service.ProjectService;
import com.tb.service.ProjectTodayService;
import com.tb.vo.AddProjectListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectTodayServiceImpl implements ProjectTodayService {

    @Autowired
    private ProjecttodayMapper projecttodayMapper;
    @Autowired
    private BankuserMapper bankuserMapper;
    @Autowired
    private ReviewerMapper reviewerMapper;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public List<AddProjectListVo> addprojectlist(String appkey){
        List<Projecttoday> projecttodayList=projecttodayMapper.selectAll();
        List<AddProjectListVo> voList=new ArrayList<>();
        for (Projecttoday p:projecttodayList){
            AddProjectListVo vo=new AddProjectListVo();
            BeanUtils.copyProperties(p,vo);
            vo.setProjectName(p.getPname());
            vo.setOwnerName(bankuserMapper.selectByAppkey(p.getOwnerId()));
            String status=p.getReviewerlist();
            vo.setProjectResult("ing");
            if(status!=null){
                String[] statuslist=status.split(",");
                for (String s:statuslist){
                    if(appkey.equals(s)){
                        vo.setProjectResult("over");
                        break;
                    }
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Projecttoday addprojectdetail(String projectId) {
        return projecttodayMapper.selectByPojectId(projectId);
    }

    @Override
    public int result(String projectId, String appkey, Boolean result) {
        int sum=0;
        //更新票数
        if(result==true){
            sum+=projecttodayMapper.updateAgree(projectId);
        }else {
            sum+=projecttodayMapper.updateDisagree(projectId);
        }
        //项目还未通过审核
        if(projecttodayMapper.selectFlag(projectId)!=1){
            //项目于此刻就审核通过了，在正式的项目表中建立项目
            if(projecttodayMapper.selectAgree(projectId)>10){
                try {
                    create(projectId);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        //更新审核人处理的任务的一些相关信息
        sum+=reviewerMapper.updateAddTask(appkey);
        String reviewerlist=projecttodayMapper.selectReviewerlist(projectId);
        Map<String,String> map=new HashMap<>();
        map.put("projectId",projectId);
        //更新该项目审核人集
        if(reviewerlist==null||"".equals(reviewerlist)){
            reviewerlist=appkey;
        }else{
            reviewerlist=reviewerlist+","+appkey;
        }
        map.put("reviewerlist",reviewerlist);
        sum+= projecttodayMapper.updateStatus(map);
        return sum;
    }

    @Override
    public int projectinfo(Map<String, Object> map1) {
        String projectId=(String)map1.get("projectId");
        Projecttoday projecttoday1=projecttodayMapper.selectByPojectId(projectId);
        Projecttoday record=new Projecttoday();
        record.setProjectId(projectId);
        record.setId(projecttoday1.getId());
        record.setPname((String)map1.get("projectName"));//设置项目名字
        record.setOwnerId((String)map1.get("appkey"));//设置发起人唯一用户id
        record.setContactPersonName((String)map1.get("contactPersonName"));//设置项目联系人姓名
        record.setContactPersonPhone((String)map1.get("contactPersonPhone"));//项目联系人电话
        record.setContactPersonSex((String)map1.get("contactPersonSex"));//项目联系人性别
        record.setNeedPeople((Integer)map1.get("needPeople"));//项目所需人数
        String value=(String) map1.get("value");
        record.setCurrency(Double.parseDouble(value));//给予每个志愿者的时间币
        String workTime=(String) map1.get("workTime");
        record.setTime(Double.parseDouble(workTime));//每个志愿者所需要工作的时间
        record.setDescription((String) map1.get("description"));//项目描述
        record.setCategory((String) map1.get("category"));//项目描述
        record.setAddress((String) map1.get("address"));//项目地址
        record.setProjectAgree(projecttoday1.getProjectAgree());
        record.setProjectDisagree(projecttoday1.getProjectDisagree());
        record.setReviewerlist(projecttoday1.getReviewerlist());
        record.setFlag(projecttoday1.getFlag());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String st= (String) map1.get("startTime");
        String et= (String) map1.get("endTime");
        try {
            Date std=format.parse(st);
            Date etd=format.parse(et);
            record.setStartTime(std);//项目开始时间
            record.setEndTime(etd);//项目结束时间
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return projecttodayMapper.updateByPojectId(record);
    }

    //判断所获同意票数超过半数（10）后，在项目总表中建立项目
    private void create(String projectId) throws ParseException {
        Project record=new Project();
        projecttodayMapper.updateFlag(projectId);
        Projecttoday projecttoday=projecttodayMapper.selectByPojectId(projectId);
        BeanUtils.copyProperties(projecttoday,record);
        record.setOwnerName(bankuserMapper.selectByAppkey(projecttoday.getOwnerId()));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date=format.format(new Date());
        Date createTime=format.parse(date);
        record.setCreateTime(createTime);
        record.setState("可承接");
        record.setNowPeople(0);
        projectMapper.insert(record);
    }


}
