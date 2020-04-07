package com.macro.mall.mapper;

import com.macro.mall.model.SmsHomeGuessYouLikeProduct;
import com.macro.mall.model.SmsHomeGuessYouLikeProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsHomeGuessYouLikeProductMapper {
    long countByExample(SmsHomeGuessYouLikeProductExample example);

    int deleteByExample(SmsHomeGuessYouLikeProductExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SmsHomeGuessYouLikeProduct record);

    int insertSelective(SmsHomeGuessYouLikeProduct record);

    List<SmsHomeGuessYouLikeProduct> selectByExample(SmsHomeGuessYouLikeProductExample example);

    SmsHomeGuessYouLikeProduct selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SmsHomeGuessYouLikeProduct record, @Param("example") SmsHomeGuessYouLikeProductExample example);

    int updateByExample(@Param("record") SmsHomeGuessYouLikeProduct record, @Param("example") SmsHomeGuessYouLikeProductExample example);

    int updateByPrimaryKeySelective(SmsHomeGuessYouLikeProduct record);

    int updateByPrimaryKey(SmsHomeGuessYouLikeProduct record);
}