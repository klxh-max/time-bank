package com.tb.mapper;

import com.tb.entity.Projecttoday;
import java.util.List;
import java.util.Map;

public interface ProjecttodayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Projecttoday record);

    Projecttoday selectByPrimaryKey(Long id);

    List<Projecttoday> selectAll();

    int updateByPrimaryKey(Projecttoday record);

    Projecttoday selectByPojectId(String projectId);

    int updateAgree(String projectId);

    int updateDisagree(String projectId);

    int updateStatus(Map<String,String> map);

    int updateFlag(String projectId);

    String selectReviewerlist(String projectId);

    int selectFlag(String projectId);

    int selectAgree(String projectId);

    int updateByPojectId(Projecttoday record);

    List<Projecttoday> selectListByOwnerId(String ownerId);
}