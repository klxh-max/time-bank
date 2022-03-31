package com.tb.mapper;

import com.tb.entity.Projectrelated;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectrelatedMapper {
    int deleteByPrimaryKey(@Param("appkey") String appkey, @Param("projectId") String projectId);

    int insert(Projectrelated record);

    Projectrelated selectByPrimaryKey(@Param("appkey") String appkey, @Param("projectId") String projectId);

    List<Projectrelated> selectAll();

    int updateByPrimaryKey(Projectrelated record);

    List<Projectrelated> selectByProjectId(String projectId);

    List<Projectrelated> queryListByProjectId(String projectId);

    List<Projectrelated> queryListByAppkey(String appkey);

    List<Projectrelated> selectAppkeyByProjectIdAndUserState(
            @Param("projectId")String projectId,
            @Param("userState")String userState);

    int updateUserState(@Param("projectId") String projectId,
                        @Param("userState") String state,
                        @Param("appkey") String appkey);

}