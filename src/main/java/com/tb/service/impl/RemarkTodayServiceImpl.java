package com.tb.service.impl;

import com.tb.entity.Project;
import com.tb.entity.Projectrelated;
import com.tb.entity.Projecttoday;
import com.tb.entity.Remarktoday;
import com.tb.mapper.*;
import com.tb.service.RemarkTodayService;
import com.tb.vo.AddProjectListVo;
import com.tb.vo.DeclareProjectDetailVo;
import com.tb.vo.DeclareProjectListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RemarkTodayServiceImpl implements RemarkTodayService {

    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RemarktodayMapper remarktodayMapper;
    @Autowired
    private BankuserMapper bankuserMapper;
    @Autowired
    private ProjectrelatedMapper projectrelatedMapper;
    @Autowired
    private ReviewerMapper reviewerMapper;

    @Override
    public int declareproject(Map<String, Object> map1) {
        String projectId =(String)map1.get("projectId");
        String ownerId=(String)map1.get("appkey");
        String remarkText=(String)map1.get("remarkText");
        Remarktoday record=new Remarktoday();
        Project project= projectMapper.selectByProjectId(projectId);
        record.setProjectId(projectId);
        record.setProjectName(project.getPname());
        record.setOwnerId(ownerId);
        record.setDescription(project.getDescription());
        record.setReviewerlist("");
        record.setRemarkAgree(0);
        record.setRemarkDisagree(0);
        record.setRemarkText(remarkText);
        return remarktodayMapper.insert(record);
    }

    @Override
    public List<DeclareProjectListVo> declareprojectlist(String appkey){
        List<Remarktoday> remarktodayList= remarktodayMapper.selectAll();
        List<DeclareProjectListVo> voList=new ArrayList<>();
        for (Remarktoday r:remarktodayList){
            DeclareProjectListVo vo=new DeclareProjectListVo();
            BeanUtils.copyProperties(r,vo);
            vo.setOwnerName(bankuserMapper.selectByAppkey(r.getOwnerId()));
            vo.setOverTime(projectMapper.selectEndTime(r.getProjectId()));
            String status=r.getReviewerlist();
            vo.setState("ing");
            if(status!=null){
                String[] statuslist=status.split(",");
                for (String s:statuslist){
                    if(appkey.equals(s)){
                        vo.setState("over");
                        break;
                    }
                }
            }
            voList.add(vo);
        }
        return voList;
    }

    @Override
    public Map<String,Object> declareprojectdetail(String projectId) {
        DeclareProjectDetailVo detailVo=new DeclareProjectDetailVo();
        Project project= projectMapper.selectByProjectId(projectId);
        Remarktoday remarktoday=remarktodayMapper.selectByProjectId(projectId);
        BeanUtils.copyProperties(project,detailVo);
        detailVo.setRemarkText(remarktoday.getRemarkText());
        detailVo.setRemarkAgree(remarktoday.getRemarkAgree());
        detailVo.setRemarkDisagree(remarktoday.getRemarkDisagree());
        detailVo.setProjectName(remarktoday.getProjectName());
        Map<String,Object> map=new HashMap<>();
        map.put("declareProjectDetailVo",detailVo);
        List<Projectrelated> projectrelatedList=projectrelatedMapper.selectByProjectId(projectId);
        map.put("userList",projectrelatedList);
        return map;
    }

    @Override
    public int result(String projectId, String appkey, Boolean result) {
        int sum=0;
        //更新票数
        if(result==true){
            sum+=remarktodayMapper.updateAgree(projectId);
        }else {
            sum+=remarktodayMapper.updateDisagree(projectId);
        }
        //申报通过更新project表中申报是否通过的标志
        if(remarktodayMapper.selectAgree(projectId)>10){
            projectMapper.updateFlag(projectId);
            //将质押金多余部分退回项目发起人账户
            Project project=projectMapper.selectByProjectId(projectId);
            Double currency=project.getCurrency();
            int needP=project.getNeedPeople();
            int nowP=project.getNowPeople();
            Double spareMoney=currency*(needP-nowP)+needP;
            spareMoney+=bankuserMapper.selectBankUserByAppkey(appkey).getUserMoney();
            bankuserMapper.updateMoneyByPrimaryKey(appkey,spareMoney);
            //将时间币发给已完成用户
            //将完成任务用户的志愿时长加上
            List<Projectrelated> projectrelatedList=projectrelatedMapper.selectAppkeyByProjectIdAndUserState(projectId,"finish");
            Double time=project.getTime();
            for (Projectrelated pr :projectrelatedList){
                String key=pr.getAppkey();
                bankuserMapper.updateCurrency(key,currency);
                bankuserMapper.updateWorkTime(key,time);
            }

        }
        //更新审核人处理的任务的一些相关信息
        sum+=reviewerMapper.updateDeclareTask(appkey);
        String reviewerlist=remarktodayMapper.selectReviewerlist(projectId);
        Map<String,String> map=new HashMap<>();
        map.put("projectId",projectId);
        //更新该项目审核人集
        if(reviewerlist==null||"".equals(reviewerlist)){
            reviewerlist=appkey;
        }else{
            reviewerlist=reviewerlist+","+appkey;
        }
        map.put("reviewerlist",reviewerlist);
        sum+= remarktodayMapper.updateStatus(map);
        return sum;
    }


}
