package com.fz.boot.sharding.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fz.boot.sharding.entity.OrderItem;
import com.fz.boot.sharding.mapper.OrderItemMapper;
import com.fz.boot.sharding.service.OrderItemService;
import org.springframework.stereotype.Service;

/**
 * @Description 实现类
 * @Author fangzheng
 * @Date 2020/9/4 14:00
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
}
