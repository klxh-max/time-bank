package com.tb.service.impl;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.tb.entity.*;
import com.tb.mapper.*;
import com.tb.service.ReviewerService;
import com.tb.vo.CandidateVo;
import com.tb.vo.FinishProVo;
import com.tb.vo.ReportResultVo;
import com.tb.vo.ReviewerInfoVo;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewerServiceImpl implements ReviewerService {

    @Autowired
    ReviewerMapper reviewerMapper;

    @Autowired
    BankuserMapper bankuserMapper;

    @Autowired
    ReportMapper reportMapper;

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    ProjecttodayMapper projecttodayMapper;

    @Autowired
    ProjectrelatedMapper projectrelatedMapper;

    @Override
    public int applyReviewer(Map<String, Object> map1) {

        Reviewer reviewer1 = reviewerMapper.selectByPrimaryKey((String) map1.get("appkey"));
        if( reviewer1 != null ){
            return 0;
        }

        Reviewer reviewer = new Reviewer();

        reviewer.setAppkey((String) map1.get("appkey"));
        reviewer.setUserIDNumber((String) map1.get("identity_number"));
        reviewer.setUserRealName((String) map1.get("name"));
        reviewer.setDesc((String) map1.get("desc"));
        reviewer.setXuanyan((String) map1.get("xuanyan"));
        reviewer.setDeclaration((String) map1.get("declaration"));
        reviewer.setStatus(0);
        reviewer.setTasksNumber(0);
        reviewer.setReportTasks(0);
        reviewer.setReviewAddTasks(0);
        reviewer.setReviewDeclareTasks(0);
        reviewer.setVotenum(0);

        return reviewerMapper.insert(reviewer);
    }

    @Override
    public int vote(Map<String, Object> map1) {

        int votes = (int) map1.get("votes");
        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey((String) map1.get("appkey"));
        Reviewer reviewer = reviewerMapper.selectByPrimaryKey((String) map1.get("id"));
        if(votes == 1){
            reviewer.setVotenum(reviewer.getVotenum()+1);
            reviewerMapper.updateByPrimaryKey(reviewer);
            bankuser.setVoteId((String) map1.get("id"));
        }else {
            reviewer.setVotenum(reviewer.getVotenum()-1);
            reviewerMapper.updateByPrimaryKey(reviewer);
            bankuser.setVoteId("");
        }

        return bankuserMapper.updateByPrimaryKey(bankuser);
    }

    @Override
    public Map<String, Object> candidateInfo(String appkey, String id) {

        Map<String,Object> map = new HashMap<>();
        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey(id);
        Reviewer reviewer = reviewerMapper.selectByPrimaryKey(id);
        map.put("name",bankuser.getUsername());
        map.put("level", bankuser.getLevel());
        map.put("workTime", bankuser.getUserWorkTime());
        map.put("xuanyan",reviewer.getXuanyan());
        map.put("desc",reviewer.getDesc());
        map.put("reviewerDays",bankuser.getReviewerNum());
        return map;
    }

    @Override
    public List<ReviewerInfoVo> reviewerList(String appkey) {
        List<ReviewerInfoVo> reviewerInfoVoList = new ArrayList<>();
        List<Reviewer> reviewerList = reviewerMapper.selectAll();
        for(Reviewer r:reviewerList){
            System.out.println(r.getStatus());
            if(r.getStatus() > 0){
                ReviewerInfoVo rf = new ReviewerInfoVo();
                Bankuser bankuser = bankuserMapper.selectBankUserByAppkey(r.getAppkey());
                rf.setId(r.getAppkey());
                rf.setAvatar(bankuser.getAvatarUrl());
                rf.setDeclaration(r.getDeclaration());
                rf.setName(bankuser.getUsername());
                rf.setTasksNumber(r.getTasksNumber());
                rf.setReportTasks(r.getReportTasks());
                rf.setReviewTasks(r.getReviewAddTasks()+r.getReviewDeclareTasks());
                rf.setReviewAddTasks(r.getReviewAddTasks());
                rf.setReviewDeclareTasks(r.getReviewDeclareTasks());
                reviewerInfoVoList.add(rf);
            }

        }
        return reviewerInfoVoList;
    }

    @Override
    public List<Report> reportReviewlist(String appkey) {
        List<Report> list = reportMapper.selectAll();
        return list;
    }

    @Override
    public int reportResult(ReportResultVo r1) {

        String appkey =  r1.getAppkey();
        String userId = r1.getUserId();
        String category = r1.getCategory();
        String id = r1.getId();
        Boolean result = r1.getResult();

        Report report = reportMapper.selectByPrimaryKey(id,category,userId);
        if (result){
            String agreeReviewIds = report.getAgreeReviewIds();
            if(agreeReviewIds == null || "".equals(agreeReviewIds)){
                report.setAgreeReviewIds(appkey);
            }else {
                agreeReviewIds = agreeReviewIds + ',' + appkey;
                report.setAgreeReviewIds(agreeReviewIds);
            }
            report.setAgree(report.getAgree()+1);
        }else {
            String disagreeReviewIds = report.getDisagreeReviewIds();
            if(disagreeReviewIds == null || "".equals(disagreeReviewIds)){
                report.setDisagreeReviewIds(appkey);
            }else {
                disagreeReviewIds = disagreeReviewIds + ',' + appkey;
                report.setDisagreeReviewIds(disagreeReviewIds);
            }
            report.setDisagree(report.getDisagree()+1);
        }


        Reviewer reviewer = reviewerMapper.selectByPrimaryKey(appkey);
        reviewer.setReportTasks(reviewer.getReportTasks()+1);

        reviewerMapper.updateByPrimaryKey(reviewer);

        return reportMapper.updateByPrimaryKey(report);
    }

    @Override
    public Map<String,Object> getreviewerInfo(String appkey) {

        Map<String,Object> map = new HashMap<>();

        Bankuser bankuser = bankuserMapper.selectBankUserByAppkey(appkey);
        String likeName;
        if(bankuser.getVoteId() == null || "".equals(bankuser.getVoteId())){
            likeName = "";
        }else {
            likeName = bankuser.getVoteId();
        }

        List<Reviewer> reviewerList = reviewerMapper.selectAll();
        List<CandidateVo> candidateVoList = new ArrayList<>();
        Bankuser bankuser1 = new Bankuser();
        for (Reviewer r:reviewerList){
            if(r.getStatus() == 0){
                bankuser1 = bankuserMapper.selectBankUserByAppkey(r.getAppkey());
                CandidateVo candidateVo = new CandidateVo();
                candidateVo.setName(bankuser1.getUsername());
                candidateVo.setId(r.getAppkey());
                candidateVo.setAvatar(bankuser1.getAvatarUrl());
                candidateVo.setXuanyan(r.getXuanyan());
                candidateVo.setVotes(r.getVotenum());
                candidateVoList.add(candidateVo);
            }
        }

        map.put("likeName",likeName);
        map.put("candidate",candidateVoList);
        return map;
    }

    @Override
    public void updateReviewByday() {
        //将上一天的审核人清楚
        reviewerMapper.updateReviewLastday();
        //根据候选人票数更新审核人
        reviewerMapper.updateReviewByday();
    }


    @Override
    public Map<String, Object> myaddproject(String appkey){

        Map<String,Object> map = new HashMap<>();

        List<Project> doing = new ArrayList<>();
        List<Projecttoday> waitCheck = new ArrayList<>();
        List<Project> finishpro = new ArrayList<>();
        List<FinishProVo> finish = new ArrayList<>();



        List<Project> projectList = projectMapper.queryListByOwnerId(appkey);
        for (Project p:projectList){
            if (!"已结束".equals(p.getState())){
                doing.add(p);
            }else {
                finishpro.add(p);
            }

        }

        List<Projecttoday> projecttodayList = projecttodayMapper.selectListByOwnerId(appkey);
        for (Projecttoday p:projecttodayList){
            if (p.getFlag()==0){
                waitCheck.add(p);
            }
        }


        for (Project p:finishpro){

            FinishProVo f = new FinishProVo();
            f.setProjectId(p.getProjectId());
            f.setPname(p.getPname());
            f.setDescription(p.getDescription());
            f.setNeedPeople(p.getNeedPeople());
            f.setNowPeople(p.getNowPeople());
            f.setCategory(p.getCategory());
            f.setCurrency(p.getCurrency());
            f.setTime(p.getTime());
            f.setAddress(p.getAddress());
            f.setContactPersonName(p.getContactPersonName());
            f.setCreateTime(p.getCreateTime());
            f.setStartTime(p.getStartTime());
            f.setEndTime(p.getEndTime());

            String recipient = p.getRecipient();
            if (recipient==null || "".equals(recipient)){
                continue;
            }

            String[] bankuserIds = recipient.split(",");
            List<Projectrelated> userList = new ArrayList<>();
            for(String b:bankuserIds){
                Projectrelated projectrelated = projectrelatedMapper.selectByPrimaryKey(b,p.getProjectId());
                userList.add(projectrelated);
            }

            f.setUserList(userList);

            finish.add(f);

        }

        map.put("doing",doing);
        map.put("waitCheck",waitCheck);
        map.put("finish",finish);

        return map;
    }


}
