package com.soap.service;

import com.soap.entity.query.GoodsQuery;

/**
 * Created by ZhangPY on 2021/1/24
 * Belong Organization OVERUN-9299
 * overun9299@163.com
 * Explain: GoodsService
 */
public interface GoodsService {

    /**
     * 分页查询商品
     * @param goodsQuery
     * @return
     */
    String getGoodsListByPage(GoodsQuery goodsQuery);

    /**
     * 批量插入goods
     * @param goods
     * @return
     */
    String batchAddGoods(String goods);
}
