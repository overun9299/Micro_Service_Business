package com.soap.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soap.commons.ReturnCommons;
import com.soap.entity.po.Goods;
import com.soap.entity.po.Orders;
import com.soap.entity.po.Person;
import com.soap.entity.query.GoodsQuery;
import com.soap.feign.service.MSUserServiceFeign;
import com.soap.manager.GoodsManager;
import com.soap.manager.GoodsStockManager;
import com.soap.manager.OrderManager;
import com.soap.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

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
    @Autowired
    private MSUserServiceFeign msUserServiceFeign;
    @Autowired
    private OrderManager orderManager;
    @Autowired
    private GoodsStockManager goodsStockManager;

    private ReentrantLock lock = new ReentrantLock();

    @Override
    public String getGoodsListByPage(GoodsQuery goodsQuery) {
        Page<Goods> page = new Page<>(goodsQuery.getPage(), goodsQuery.getLimit());
        QueryWrapper<Goods> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("create_date");
        IPage<Goods> goodsIPage = goodsManager.page(page , wrapper);
        return ReturnCommons.getSuccess(goodsIPage);
    }

    @Override
    public String batchAddGoods(String goods) {
        if (StringUtils.isNotBlank(goods)) {
            try {
                List<Goods> goodsList = JSONArray.parseArray(goods, Goods.class);
                if (goodsList.size() > 0) {
                    /** 批量插入时间 **/
                    goodsList.forEach(good -> {
                        good.setCreateDate(LocalDateTime.now());
                    });
                    goodsManager.saveBatch(goodsList);
                    return ReturnCommons.getSuccess();
                }
            } catch (Exception e) {
                log.error("批量插入good方法,转换json异常 {}" , e.getMessage());
            }

        }
        return ReturnCommons.getFail();
    }

    @Transactional
    @Override
    public String bugGoods(Long personId, Long goodsId) {
        if (personId == null || goodsId == null) {
            return ReturnCommons.getFail("参数异常");
        }

        /** 查询person **/
        String personById = msUserServiceFeign.getPersonById(personId);
        if (StringUtils.isNotBlank(personById)) {
            JSONObject jsonObject = JSONObject.parseObject(personById);
            Object data = jsonObject.get("data");
            if (data != null ) {
                Person person = JSONObject.parseObject(data.toString(), Person.class);
                if (person == null) {
                    return ReturnCommons.getFail("不存在该人员");
                }
                /** 查询goods **/
                Goods goods = goodsManager.getById(goodsId);
                if (goods == null) {
                    return ReturnCommons.getFail("不存在该商品");
                }
                /** 减库存 **/
                int inventoryReduction = goodsStockManager.inventoryReduction(goodsId);
                if (inventoryReduction > 0) {
                    Orders orders = new Orders();
                    /** id **/
                    orders.setId(RandomUtil.simpleUUID());
                    /** person相关 **/
                    orders.setPersonId(person.getId());
                    orders.setPersonName(person.getFullName());
                    orders.setPersonPhone(person.getPhone());
                    /** goods相关 **/
                    orders.setGoodsId(goods.getId());
                    orders.setGoodsPrice(goods.getPrice());
                    orders.setGoodsTitle(goods.getTitle());
                    /** 地址、类型、创建时间 **/
                    orders.setAddress(person.getAddress());
                    orders.setState(0);
                    orders.setCreateTime(LocalDateTime.now());

                    boolean save = orderManager.save(orders);

                    if (save) {
                        return ReturnCommons.getSuccess(orders);
                    }
                } else {
                    return ReturnCommons.getFail("库存不足");
                }

            }

        }

        return ReturnCommons.getFail("购买失败");
    }


    @Override
    public String highConcurrency(Long goodsId) {

        int i = goodsStockManager.inventoryReduction(goodsId);
        if (i > 0) {
            log.info("抢购成功");
        } else {
//            log.info("抢购不成功");
        }
        return null;
    }
}
