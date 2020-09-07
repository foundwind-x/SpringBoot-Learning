package com.fz.boot.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fz.boot.sharding.entity.Order;
import com.fz.boot.sharding.mapper.OrderMapper;
import com.fz.boot.sharding.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @Description 实现类
 * @Author fangzheng
 * @Date 2020/9/4 13:56
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
