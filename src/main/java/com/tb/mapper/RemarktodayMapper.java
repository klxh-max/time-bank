package com.tb.mapper;

import com.tb.entity.Remarktoday;
import java.util.List;
import java.util.Map;

public interface RemarktodayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Remarktoday record);

    Remarktoday selectByPrimaryKey(Long id);

    List<Remarktoday> selectAll();

    int updateByPrimaryKey(Remarktoday record);

    Remarktoday selectByProjectId(String projectId);

    int updateAgree(String projectId);

    int updateDisagree(String projectId);

    String selectReviewerlist(String projectId);

    int selectAgree(String projectId);

    int updateStatus(Map<String,String> map);
}