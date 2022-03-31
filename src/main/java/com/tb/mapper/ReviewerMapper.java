package com.tb.mapper;

import com.tb.entity.Reviewer;
import java.util.List;
import java.util.Map;

public interface ReviewerMapper {
    int deleteByPrimaryKey(String appkey);

    int insert(Reviewer record);

    Reviewer selectByPrimaryKey(String appkey);

    List<Reviewer> selectAll();

    int updateByPrimaryKey(Reviewer record);

    int updateAddTask(String appkey);

    int updateDeclareTask(String appkey);

    void updateReviewByday();

    void updateReviewLastday();

    Map<String,Object> myaddproject();
}