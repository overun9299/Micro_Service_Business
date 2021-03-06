package com.soap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.soap.entity.po.GoodsStock;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品库存表 Mapper 接口
 * </p>
 *
 * @author ZhangPY
 * @since 2021-03-13
 */
public interface GoodsStockMapper extends BaseMapper<GoodsStock> {

    int inventoryReduction(@Param("goodsId") Long goodsId);
}
