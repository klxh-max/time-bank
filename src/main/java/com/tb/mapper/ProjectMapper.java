package com.tb.mapper;

import com.tb.entity.Project;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Project record);

    Project selectByPrimaryKey(Long id);

    List<Project> selectAll();

    int updateByPrimaryKey(Project record);

    Date selectEndTime(String projectId);

    int updateState(@Param("projectId") String projectId,@Param("state") String state);

    Project selectByProjectId(String projectId);

    int updateFlag(String projectId);

    int selectFlag(String projectId);

    Project queryProjectByProjectId(String projectId);

    List<Project> queryListByOwnerId(String ownerId);

    void updateProjectStatus();
}