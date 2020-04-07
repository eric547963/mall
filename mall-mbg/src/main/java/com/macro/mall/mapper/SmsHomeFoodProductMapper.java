package com.macro.mall.mapper;

import com.macro.mall.model.SmsHomeFoodProduct;
import com.macro.mall.model.SmsHomeFoodProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeFoodProductMapper {
    long countByExample(SmsHomeFoodProductExample example);

    int deleteByExample(SmsHomeFoodProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeFoodProduct record);

    int insertSelective(SmsHomeFoodProduct record);

    List<SmsHomeFoodProduct> selectByExample(SmsHomeFoodProductExample example);

    SmsHomeFoodProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomeFoodProduct record, @Param("example") SmsHomeFoodProductExample example);

    int updateByExample(@Param("record") SmsHomeFoodProduct record, @Param("example") SmsHomeFoodProductExample example);

    int updateByPrimaryKeySelective(SmsHomeFoodProduct record);

    int updateByPrimaryKey(SmsHomeFoodProduct record);
}