package com.macro.mall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeGuessYouLikeProductMapper;
import com.macro.mall.model.SmsHomeGuessYouLikeProduct;
import com.macro.mall.model.SmsHomeGuessYouLikeProductExample;
import com.macro.mall.service.SmsHomeGuessYouLikeProductService;

/**
 * 首页新品推荐管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsHomeGuessYouLikeProductServiceImpl implements SmsHomeGuessYouLikeProductService {
    @Autowired
    private SmsHomeGuessYouLikeProductMapper homeGuessYouLikeProductMapper;
    @Override
    public int create(List<SmsHomeGuessYouLikeProduct> homeNewProductList) {
        for (SmsHomeGuessYouLikeProduct SmsHomeGuessYouLikeProduct : homeNewProductList) {
            SmsHomeGuessYouLikeProduct.setRecommendStatus(1);
            SmsHomeGuessYouLikeProduct.setSort(0);
            homeGuessYouLikeProductMapper.insert(SmsHomeGuessYouLikeProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeGuessYouLikeProduct homeNewProduct = new SmsHomeGuessYouLikeProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeGuessYouLikeProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeGuessYouLikeProductExample example = new SmsHomeGuessYouLikeProductExample();
        example.createCriteria().andIdIn(ids);
        return homeGuessYouLikeProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeGuessYouLikeProductExample example = new SmsHomeGuessYouLikeProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeGuessYouLikeProduct record = new SmsHomeGuessYouLikeProduct();
        record.setRecommendStatus(recommendStatus);
        return homeGuessYouLikeProductMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeGuessYouLikeProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeGuessYouLikeProductExample example = new SmsHomeGuessYouLikeProductExample();
        SmsHomeGuessYouLikeProductExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(productName)){
            criteria.andProductNameLike("%"+productName+"%");
        }
        if(recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeGuessYouLikeProductMapper.selectByExample(example);
    }
}
