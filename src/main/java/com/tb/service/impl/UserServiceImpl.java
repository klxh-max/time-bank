package com.tb.service.impl;

import com.tb.entity.Bankuser;
import com.tb.entity.Project;
import com.tb.entity.Projectrelated;
import com.tb.mapper.BankuserMapper;
import com.tb.mapper.ProjectMapper;
import com.tb.mapper.ProjectrelatedMapper;
import com.tb.mapper.ReviewerMapper;
import com.tb.service.UserService;
import com.tb.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BankuserMapper bankuserMapper;

    @Autowired
    private ReviewerMapper reviewerMapper;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectrelatedMapper projectrelatedMapper;

    @Override
    public UserVo login(String phoneNumber, String password) {
        Bankuser bankuser = bankuserMapper.selectByPhoneNumber(phoneNumber);
        UserVo userVo = new UserVo();
        if (bankuser != null && password.equals(bankuser.getPassword())) {
            //将bankuser和userVo属性名相同且类型相同的拷贝过去
            BeanUtils.copyProperties(bankuser, userVo);
        }
        return userVo;
    }

    @Override
    public int acceptproject(Map<String, Object> map1) {

        //用户是否已提交申请
        String newProjectId = (String) map1.get("projectId");
        String newAppkey = (String) map1.get("appkey");

        if (projectrelatedMapper.selectByPrimaryKey(newAppkey, newProjectId) != null) {
            return 2;//已提交申请
        }

        //增添关系到projectrelated表中
        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey((String) map1.get("appkey"));
        Projectrelated projectrelated = new Projectrelated();
        projectrelated.setAppkey(newAppkey);
        projectrelated.setProjectId(newProjectId);
        //projectrelated.setUserState("doing");
        projectrelated.setAcceptTime(new Date());
        projectrelated.setUsername(bankuser.getUsername());
        projectrelated.setUserAvatar(bankuser.getAvatarUrl());
        projectrelated.setReviewState("未审核");
        return projectrelatedMapper.insert(projectrelated);


//        //用户是否承接过此项目
//        Boolean isExist = false;
//        String newProjectId = (String)map1.get("projectId");
//        //修改bankuser表中承接项目id集
//        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey((String)map1.get("appkey"));
//        String projectIdIns = bankuser.getProjectIdIn();
//        if(projectIdIns == null){
//            bankuser.setProjectIdIn(newProjectId);
//        }else{
//            String[] projectIdInList=projectIdIns.split(",");
//            for (String s:projectIdInList){
//                if(newProjectId.equals(s)){
//                    isExist = true;
//                    break;
//                }
//            }
//            if(!isExist){
//                projectIdIns += ",";
//                projectIdIns += newProjectId;
//                bankuser.setProjectIdIn(projectIdIns);
//            }
//        }
//
//        //已承接过此项目
//        if(isExist){
//            return 2;
//        }
//        int result = bankuserMapper.updateByPrimaryKey(bankuser);
//
//        //修改project表中的承接人集和报名人数
//        String newAppkey = (String) map1.get("appkey");
//        Project project = projectMapper.queryProjectByProjectId(newProjectId);
//        String recipient = project.getRecipient();
//
//        if(recipient == null){
//            project.setRecipient(newAppkey);
//        }else{
//
//            recipient += ",";
//            recipient += newAppkey;
//
//            project.setRecipient(recipient);
//        }
//        int num = project.getNowPeople();
//        num++;
//        project.setNowPeople(num);
//        //判断是否满员
//        if(num == project.getNeedPeople()){
//            project.setState("已满员");
//        }
//        projectMapper.updateByPrimaryKey(project);
//
//        //增添关系到projectrelated表中
//        Projectrelated projectrelated = new Projectrelated();
//        projectrelated.setAppkey(newAppkey);
//        projectrelated.setProjectId(newProjectId);
//        projectrelated.setUserState("doing");
//        projectrelated.setAcceptTime(new Date());
//        projectrelated.setUsername(bankuser.getUsername());
//        projectrelated.setUserAvatar(bankuser.getAvatarUrl());
//        projectrelatedMapper.insert(projectrelated);
//
//        return result;
    }

    @Override
    public List<MyProjectVo> queryMyProject(String appkey) {

        List<MyProjectVo> list = new ArrayList<>();
        List<Projectrelated> projectrelatedList = projectrelatedMapper.queryListByAppkey(appkey);
        for (Projectrelated p : projectrelatedList) {
            if ("同意".equals(p.getReviewState())) {

                MyProjectVo projectVo = new MyProjectVo();
                Project project = projectMapper.queryProjectByProjectId(p.getProjectId());
                projectVo.setId(p.getProjectId());
                projectVo.setName(project.getPname());
                projectVo.setOwnerName(project.getOwnerName());
                projectVo.setValue(project.getCurrency());
                projectVo.setTime(project.getTime());
                projectVo.setCategory(project.getCategory());
                projectVo.setAcceptTime(p.getAcceptTime());
                projectVo.setStartTime(project.getStartTime());
                projectVo.setEndTime(project.getEndTime());
                projectVo.setFinishTime(p.getOverTime());
                if ("doing".equals(p.getUserState())) {
                    Date date = new Date();
                    if (!date.before(projectMapper.selectEndTime(project.getProjectId()))) {
                        projectrelatedMapper.updateUserState(project.getProjectId(), "waitForComment", appkey);
                        projectVo.setUserState("waitForComment");
                    }
                }
                projectVo.setUserState(p.getUserState());
                list.add(projectVo);
            }

        }
        return list;
}

    @Override
    public List<VolunteerVo> queryVolunteerlistByAppkey(String appkey) {

        List<Project> projectList = projectMapper.queryListByOwnerId(appkey);
        Bankuser user = new Bankuser();
        List<VolunteerVo> listvo = new ArrayList<>();
        for (Project pro : projectList) {

            List<Projectrelated> prList = projectrelatedMapper.queryListByProjectId(pro.getProjectId());
            if (prList != null) {
                for (Projectrelated pr : prList) {
                    if ("未审核".equals(pr.getReviewState())) {
                        VolunteerVo vo = new VolunteerVo();
                        user = bankuserMapper.selectBankUserByAppkey(pr.getAppkey());
                        vo.setAppkey(user.getAppkey());
                        vo.setUsername(user.getUsername());
                        vo.setLevel(user.getLevel());
                        vo.setProjectId(pro.getProjectId());
                        vo.setProjectName(pro.getPname());
                        vo.setUserWorkTime(user.getUserWorkTime());
                        vo.setProjectNum(user.getProjectNum());
                        vo.setAcceptTime(pr.getAcceptTime());
                        listvo.add(vo);
                    }
                }
            }

        }

        return listvo;
    }

    @Override
    public int volunteerApply(VolunteerApplyVo v) {

        String userId = v.getUserId();
        String userProject = v.getUserProject();
        boolean result = v.getResult();
        Projectrelated projectrelated = projectrelatedMapper.selectByPrimaryKey(userId, userProject);

        if (result) {
            projectrelated.setReviewState("同意");

            //修改bankuser表中承接项目id集
            Bankuser bankuser = bankuserMapper.selectBankUserByAppkey(userId);
            String projectIdIns = bankuser.getProjectIdIn();
            if (projectIdIns == null || "".equals(projectIdIns)) {
                bankuser.setProjectIdIn(userProject);
            } else {
                projectIdIns += ",";
                projectIdIns += userProject;
                bankuser.setProjectIdIn(projectIdIns);
            }
            bankuserMapper.updateByPrimaryKey(bankuser);

            //修改project表中的承接人集和报名人数
            Project project = projectMapper.queryProjectByProjectId(userProject);
            String recipient = project.getRecipient();


            if (recipient == null || "".equals(recipient)) {
                project.setRecipient(userId);
            } else {

                recipient += ",";
                recipient += userId;
                project.setRecipient(recipient);
            }


            int num = project.getNowPeople();
            num++;

            project.setNowPeople(num);
            //判断是否满员
            if (num == project.getNeedPeople()) {
                project.setState("已满员");
            }
            projectMapper.updateByPrimaryKey(project);

            projectrelated.setUserState("doing");
        } else if (!result) {
            projectrelated.setReviewState("拒绝");
        } else {
            return 2;
        }

        System.out.println(projectrelated.getReviewState());

        return projectrelatedMapper.updateByPrimaryKey(projectrelated);
    }

    @Override
    public Bankuser getuserinfo(String appkey) {
        return bankuserMapper.selectBankUserByAppkey(appkey);
    }
}
