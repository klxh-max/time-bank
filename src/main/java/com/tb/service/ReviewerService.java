package com.tb.service;

import com.tb.entity.Report;
import com.tb.vo.ReportResultVo;
import com.tb.vo.ReviewerInfoVo;

import java.util.List;
import java.util.Map;


public interface ReviewerService {

    int applyReviewer(Map<String,Object> map1);

    int vote(Map<String,Object> map1);

    Map<String,Object> candidateInfo(String appkey,String id);

    List<ReviewerInfoVo> reviewerList(String appkey);

    List<Report> reportReviewlist(String appkey);

    int reportResult(ReportResultVo r1);

    Map<String,Object> getreviewerInfo(String appkey);

    void updateReviewByday();

    Map<String,Object> myaddproject(String appkey);

}
