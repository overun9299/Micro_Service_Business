package com.soap.manager.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soap.entity.po.Order;
import com.soap.manager.OrderManager;
import com.soap.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZhangPY
 * @since 2021-03-13
 */
@Service
public class OrderManagerImpl extends ServiceImpl<OrderMapper, Order> implements OrderManager {

}
