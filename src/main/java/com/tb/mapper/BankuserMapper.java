package com.tb.mapper;

import com.tb.entity.Bankuser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BankuserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Bankuser record);

    Bankuser selectByPrimaryKey(Long id);

    List<Bankuser> selectAll();

    int updateByPrimaryKey(Bankuser record);

    //查询姓名
    String selectByAppkey(String appkey);

    Bankuser selectByPhoneNumber(String phoneNumber);

    Bankuser selectBankUserByAppkey(String appkey);

    int updateMoneyByPrimaryKey(@Param("appkey") String appkey,@Param("userMoney") Double userMoney);

    int updateCurrency(@Param("appkey") String appkey,@Param("currency") Double currency);

    int updateWorkTime(@Param("appkey") String appkey,@Param("time") Double time);

}