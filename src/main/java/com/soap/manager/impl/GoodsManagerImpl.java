package com.soap.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soap.entity.po.Goods;
import com.soap.manager.GoodsManager;
import com.soap.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangPY
 * @since 2021-01-24
 */
@Service
public class GoodsManagerImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsManager {

}
