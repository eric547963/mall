package com.macro.mall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.SmsHomeFoodProduct;
import com.macro.mall.service.SmsHomeFoodProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 推荐餐食管理Controller
 * Created by macro on 2018/11/6.
 */
@Controller
@Api(tags = "SmsHomeFoodProductController", description = "推荐餐食管理")
@RequestMapping("/home/foodProduct")
public class SmsHomeFoodProductController {
    @Autowired
    private SmsHomeFoodProductService homeFoodProductService;

    @ApiOperation("添加推荐餐食")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody List<SmsHomeFoodProduct> homeBrandList) {
        int count = homeFoodProductService.create(homeBrandList);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("修改推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateSort(@PathVariable Long id, Integer sort) {
        int count = homeFoodProductService.updateSort(id, sort);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量删除推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = homeFoodProductService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("批量修改推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = homeFoodProductService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation("分页查询推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<SmsHomeFoodProduct>> list(@RequestParam(value = "productName", required = false) String productName,
                                                            @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<SmsHomeFoodProduct> homeBrandList = homeFoodProductService.list(productName, recommendStatus, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(homeBrandList));
    }
}
