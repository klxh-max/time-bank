package com.tb.service;

import java.util.Map;

public interface ReportService {

    int addReportProject(Map<String,Object> report);

    int addReportOriginator(Map<String,Object> report);

    int addReportReviewer(Map<String,Object> report);

}
