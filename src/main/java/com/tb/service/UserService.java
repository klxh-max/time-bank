package com.tb.service;

import com.tb.entity.Bankuser;
import com.tb.entity.Projectrelated;
import com.tb.vo.MyProjectVo;
import com.tb.vo.UserVo;
import com.tb.vo.VolunteerApplyVo;
import com.tb.vo.VolunteerVo;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserVo login(String phoneNumber, String password);

    int acceptproject(Map<String,Object> map1);

    List<MyProjectVo> queryMyProject(String appkey);

    List<VolunteerVo> queryVolunteerlistByAppkey(String appkey);

    int volunteerApply(VolunteerApplyVo v);

    Bankuser getuserinfo(String appkey);
}
