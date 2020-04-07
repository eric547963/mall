package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeFoodProductMapper;
import com.macro.mall.model.SmsHomeFoodProduct;
import com.macro.mall.model.SmsHomeFoodProductExample;
import com.macro.mall.service.SmsHomeFoodProductService;
import com.macro.mall.service.SmsHomeFoodProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 首页新品推荐管理Service实现类
 * Created by macro on 2018/11/6.
 */
@Service
public class SmsHomeFoodProductServiceImpl implements SmsHomeFoodProductService {
    @Autowired
    private SmsHomeFoodProductMapper homeFoodProductMapper;
    @Override
    public int create(List<SmsHomeFoodProduct> homeNewProductList) {
        for (SmsHomeFoodProduct SmsHomeFoodProduct : homeNewProductList) {
            SmsHomeFoodProduct.setRecommendStatus(1);
            SmsHomeFoodProduct.setSort(0);
            homeFoodProductMapper.insert(SmsHomeFoodProduct);
        }
        return homeNewProductList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeFoodProduct homeNewProduct = new SmsHomeFoodProduct();
        homeNewProduct.setId(id);
        homeNewProduct.setSort(sort);
        return homeFoodProductMapper.updateByPrimaryKeySelective(homeNewProduct);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeFoodProductExample example = new SmsHomeFoodProductExample();
        example.createCriteria().andIdIn(ids);
        return homeFoodProductMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeFoodProductExample example = new SmsHomeFoodProductExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeFoodProduct record = new SmsHomeFoodProduct();
        record.setRecommendStatus(recommendStatus);
        return homeFoodProductMapper.updateByExampleSelective(record,example);
    }

    @Override
    public List<SmsHomeFoodProduct> list(String productName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsHomeFoodProductExample example = new SmsHomeFoodProductExample();
        SmsHomeFoodProductExample.Criteria criteria = example.createCriteria();
        if(!StringUtils.isEmpty(productName)){
            criteria.andProductNameLike("%"+productName+"%");
        }
        if(recommendStatus!=null){
            criteria.andRecommendStatusEqualTo(recommendStatus);
        }
        example.setOrderByClause("sort desc");
        return homeFoodProductMapper.selectByExample(example);
    }
}
