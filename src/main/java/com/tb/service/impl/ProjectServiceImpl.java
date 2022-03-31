package com.tb.service.impl;

import com.tb.entity.Bankuser;
import com.tb.entity.Project;
import com.tb.entity.Projectrelated;
import com.tb.entity.Projecttoday;
import com.tb.mapper.BankuserMapper;
import com.tb.mapper.ProjectMapper;
import com.tb.mapper.ProjectrelatedMapper;
import com.tb.mapper.ProjecttodayMapper;
import com.tb.service.ProjectService;
import com.tb.util.ProjectIDCreate;
import com.tb.vo.ProjectVo;
import org.checkerframework.checker.units.qual.A;
import org.jetbrains.annotations.Async;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjecttodayMapper projecttodayMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private ProjectrelatedMapper projectrelatedMapper;
    @Autowired
    private BankuserMapper bankuserMapper;

    @Override
    public int newproject(Map<String, Object> map1) {
        Projecttoday record=new Projecttoday();
        //注意，联系人与发起人不一定是一个人
        record.setProjectId(ProjectIDCreate.gen());//设置项目唯一id
        record.setPname((String)map1.get("projectName"));//设置项目名字
        record.setOwnerId((String)map1.get("appkey"));//设置发起人唯一用户id
        record.setContactPersonName((String)map1.get("contactPersonName"));//设置项目联系人姓名
        record.setContactPersonPhone((String)map1.get("contactPersonPhone"));//项目联系人电话
        record.setContactPersonSex((String)map1.get("contactPersonSex"));//项目联系人性别
        record.setNeedPeople((Integer)map1.get("needPeople"));//项目所需人数
        String value=(String) map1.get("value");
        record.setCurrency(Double.parseDouble(value));//给予每个志愿者的时间币
        Double money;
        money=(Integer)map1.get("needPeople")*(Double.parseDouble(value)+1);
        money=bankuserMapper.selectBankUserByAppkey((String)map1.get("appkey")).getUserMoney()-money;
        bankuserMapper.updateMoneyByPrimaryKey((String)map1.get("appkey"),money);
        String workTime=(String) map1.get("workTime");
        record.setTime(Double.parseDouble(workTime));//每个志愿者所需要工作的时间
        record.setDescription((String) map1.get("description"));//项目描述
        record.setCategory((String) map1.get("category"));//项目描述
        record.setAddress((String) map1.get("address"));//项目地址
        record.setProjectAgree(0);
        record.setProjectDisagree(0);
        record.setFlag(0);

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
        return projecttodayMapper.insert(record);
    }

    @Override
    public List<ProjectVo> projectslist() {
        List<Project> projectList= projectMapper.selectAll();
        List<ProjectVo> projectVoList=new ArrayList<>();
        Date date=new Date();
        for (Project project:projectList){
            ProjectVo projectVo=new ProjectVo();
            BeanUtils.copyProperties(project,projectVo);
            if(!date.before(projectMapper.selectEndTime(project.getProjectId()))){
                int i=projectMapper.updateState(project.getProjectId(),"已结束");
                projectVo.setState("已结束");
            }
            projectVoList.add(projectVo);
        }
        return projectVoList;
    }

    @Override
    public Map<String, Object> projectbyid(String projectId) {
        Map<String, Object> map=new HashMap<>();
        List<Projectrelated> list = new ArrayList<>();
        Project project= projectMapper.selectByProjectId(projectId);
        map.put("projectInfo",project);
        List<Projectrelated> projectrelatedList=projectrelatedMapper.selectByProjectId(projectId);
        for (Projectrelated p:projectrelatedList){
            if ("finish".equals(p.getUserState())){
                list.add(p);
            }
        }
        map.put("userList",list);
        return map;
    }

    @Override
    public int comment(Map<String, Object> map1) {

        String appkey = (String) map1.get("appkey");
        String projectId = (String) map1.get("projectId");
        Projectrelated projectrelated = projectrelatedMapper.selectByPrimaryKey(appkey,projectId);
        projectrelated.setStar((Integer) map1.get("star"));
        projectrelated.setOverTime(new Date());
        projectrelated.setUserComment((String) map1.get("comment"));
        projectrelated.setUserState("finish");
        return projectrelatedMapper.updateByPrimaryKey(projectrelated);
    }

    @Override
    public Map<String, Object> myaddproject(String appkey) {
        List<Project> doing = new ArrayList<>();
        List<Project> waitCheck = new ArrayList<>();
        List<Project> finish = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();

        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey(appkey);
        String projectIdOut = bankuser.getProjectIdOut();

        if (projectIdOut != null && !"".equals(projectIdOut)){
            String[]  projectIds = projectIdOut.split(",");
            for (String p:projectIds){
                Project project = projectMapper.queryProjectByProjectId(p);
            }
        }

        return null;
    }

    @Override
    public void updateProjectStatus(){




    }

}
