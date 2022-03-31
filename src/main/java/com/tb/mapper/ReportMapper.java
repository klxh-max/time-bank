package com.tb.mapper;

import com.tb.entity.Report;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportMapper {
    int deleteByPrimaryKey(@Param("reportId") String reportId, @Param("category") String category, @Param("ownerId") String ownerId);

    int insert(Report record);

    Report selectByPrimaryKey(@Param("reportId") String reportId, @Param("category") String category, @Param("ownerId") String ownerId);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}