package com.soap.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soap.commons.ReturnCommons;
import com.soap.entity.po.Goods;
import com.soap.entity.query.GoodsQuery;
import com.soap.manager.GoodsManager;
import com.soap.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZhangPY on 2021/1/24
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: GoodsServiceImpl
 */
@Slf4j(topic = "GoodsServiceImpl")
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsManager goodsManager;

    @Override
    public String getGoodsListByPage(GoodsQuery goodsQuery) {
        Page<Goods> page = new Page<>(goodsQuery.getPage(), goodsQuery.getLimit());
        IPage<Goods> goodsIPage = goodsManager.page(page);
        return ReturnCommons.getSuccess(goodsIPage);
    }

    @Override
    public String batchAddGoods(String goods) {
        if (StringUtils.isNotBlank(goods)) {
            try {
                List<Goods> goodsList = JSONArray.parseArray(goods, Goods.class);
                if (goodsList.size() > 0) {
                    goodsManager.saveBatch(goodsList);
                    return ReturnCommons.getSuccess();
                }
            } catch (Exception e) {
                log.error("批量插入good方法,转换json异常 {}" , e.getMessage());
            }

        }
        return ReturnCommons.getFail();
    }
}
