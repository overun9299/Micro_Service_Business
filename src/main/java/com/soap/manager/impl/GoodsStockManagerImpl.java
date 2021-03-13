package com.soap.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soap.entity.po.GoodsStock;
import com.soap.manager.GoodsStockManager;
import com.soap.mapper.GoodsStockMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品库存表 服务实现类
 * </p>
 *
 * @author ZhangPY
 * @since 2021-03-13
 */
@Service
public class GoodsStockManagerImpl extends ServiceImpl<GoodsStockMapper, GoodsStock> implements GoodsStockManager {

}
