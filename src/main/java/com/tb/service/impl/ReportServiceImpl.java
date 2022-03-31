package com.tb.service.impl;

import com.tb.entity.Report;
import com.tb.mapper.BankuserMapper;
import com.tb.mapper.ProjectMapper;
import com.tb.mapper.ReportMapper;
import com.tb.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportMapper reportMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private BankuserMapper bankuserMapper;

    @Override
    public int addReportProject(Map<String,Object> map1) {

        //projectId不存在
        if(projectMapper.queryProjectByProjectId((String)map1.get("projectId")) == null){
            return 2;
        }
        //appkey不存在
        if(bankuserMapper.selectBankUserByAppkey((String)map1.get("appkey")) == null){
            return 3;
        }

        if(reportMapper.selectByPrimaryKey((String)map1.get("projectId"),"举报项目",(String)map1.get("appkey")) != null){
            return 4;
        }

        Report record = new Report();
        record.setReportId((String)map1.get("projectId"));
        record.setReportName((String)map1.get("projectName"));
        record.setDesc((String)map1.get("desc"));
        record.setTime(new Date());
        record.setCategory("举报项目");
        record.setOwnerName((String)map1.get("name"));
        record.setOwnerId((String)map1.get("appkey"));
        record.setPhoneNumber((String)map1.get("phone"));
        record.setAgree(0);
        record.setDisagree(0);
        record.setTips("ing");
        return reportMapper.insert(record);
    }


    @Override
    public int addReportOriginator(Map<String,Object> map1){

        //发起者ID不存在
        if(bankuserMapper.selectBankUserByAppkey((String)map1.get("originatorId")) == null){
            return 2;
        }
        //appkey不存在
        if(bankuserMapper.selectBankUserByAppkey((String)map1.get("appkey")) == null){
            return 3;
        }

        if(reportMapper.selectByPrimaryKey((String)map1.get("originatorId"),"举报发起者",(String)map1.get("appkey")) != null){
            return 4;
        }
        Report record = new Report();
        record.setReportId((String)map1.get("originatorId"));
        record.setReportName((String)map1.get("originatorName"));
        record.setDesc((String)map1.get("desc"));
        record.setTime(new Date());
        record.setCategory("举报发起者");
        record.setOwnerName((String)map1.get("name"));
        record.setOwnerId((String)map1.get("appkey"));
        record.setPhoneNumber((String)map1.get("phone"));
        record.setAgree(0);
        record.setDisagree(0);
        record.setTips("ing");
        return reportMapper.insert(record);
    }

    @Override
    public int addReportReviewer(Map<String, Object> map1) {

        //审核人ID不存在
        if(bankuserMapper.selectBankUserByAppkey((String)map1.get("reviewerId")) == null){
            return 2;
        }
        //appkey不存在
        if(bankuserMapper.selectBankUserByAppkey((String)map1.get("appkey")) == null){
            return 3;
        }

        if(reportMapper.selectByPrimaryKey((String)map1.get("reviewerId"),"举报审核人",(String)map1.get("appkey")) != null){
            return 4;
        }
        Report record = new Report();
        record.setReportId((String)map1.get("reviewerId"));
        record.setReportName((String)map1.get("reviewerName"));
        record.setDesc((String)map1.get("desc"));
        record.setTime(new Date());
        record.setCategory("举报审核人");
        record.setOwnerName((String)map1.get("name"));
        record.setOwnerId((String)map1.get("appkey"));
        record.setPhoneNumber((String)map1.get("phone"));
        record.setAgree(0);
        record.setDisagree(0);
        record.setTips("ing");
        return reportMapper.insert(record);
    }


}
