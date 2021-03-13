package com.soap.controller;


import com.soap.entity.query.GoodsQuery;
import com.soap.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ZhangPY
 * @since 2021-01-24
 */
@RestController
@Api(value = "商品模块" , tags = "商品模块")
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @ApiOperation(value = "分页查询商品")
    @RequestMapping(value = "/getGoodsListByPage" , method = RequestMethod.POST)
    public String getGoodsListByPage(HttpServletRequest request, GoodsQuery goodsQuery) {
        return goodsService.getGoodsListByPage(goodsQuery);
    }

    @ApiOperation(value = "批量插入goods")
    @RequestMapping(value = "/batchAddGoods")
    public String batchAddGoods(@RequestBody String goods) {
        return goodsService.batchAddGoods(goods);
    }

    @ApiOperation(value = "抢购goods")
    @RequestMapping(value = "/bugGoods")
    public String bugGoods(Long personId , Long goodsId ) {
        return goodsService.bugGoods(personId , goodsId);
    }
}
