package com.soap.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.soap.commons.ReturnCommons;
import com.soap.entity.po.Goods;
import com.soap.entity.po.Order;
import com.soap.entity.po.Person;
import com.soap.entity.query.GoodsQuery;
import com.soap.feign.service.MSUserServiceFeign;
import com.soap.manager.GoodsManager;
import com.soap.manager.OrderManager;
import com.soap.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
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

    @Autowired
    private MSUserServiceFeign msUserServiceFeign;

    @Autowired
    private OrderManager orderManager;

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



                Order order = new Order();
                /** id **/
                order.setId(RandomUtil.simpleUUID());
                /** person相关 **/
                order.setPersonId(person.getId());
                order.setPersonName(person.getFullName());
                order.setPersonPhone(person.getPhone());
                /** goods相关 **/
                order.setGoodsId(goods.getId());
                order.setGoodsPrice(goods.getPrice());
                order.setGoodsTitle(goods.getTitle());
                /** 地址、类型、创建时间 **/
                order.setAddress(person.getAddress());
                order.setState(0);
                order.setCreateTime(LocalDateTime.now());

                boolean save = orderManager.save(order);

                if (save) {
                    return ReturnCommons.getSuccess(order);
                }

            }

        }

        return ReturnCommons.getFail("购买失败");
    }
}
