package com.fz.boot.sharding.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.fz.boot.sharding.entity.Order;
import com.fz.boot.sharding.entity.OrderItem;
import com.fz.boot.sharding.service.OrderItemService;
import com.fz.boot.sharding.service.OrderService;
import com.fz.boot.sharding.vo.OrderItemVo;
import com.fz.boot.sharding.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author fangzheng
 * @Date 2020/9/4 14:01
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @PostMapping("saveAll")
    public Boolean save(@RequestBody OrderVo orderVo){
        System.out.println(orderVo);
        Order order = new Order();
        order.setOrderId(orderVo.getOrderId());
        order.setUserId(orderVo.getUserId());
        order.setAddressId(orderVo.getAddressId());
        order.setStatus(orderVo.getStatus());
        orderService.save(order);
        System.out.println("====="+JSONUtil.toJsonStr(order));

        List<OrderItemVo> orderItemVos = orderVo.getOrderItemVos();
        List<OrderItem> orderItems = new ArrayList<>();
        orderItemVos.forEach(orderItemVo -> {
            OrderItem item = new OrderItem();
            item.setOrderId(orderVo.getOrderId());
            item.setUserId(orderVo.getUserId());
            item.setStatus(orderVo.getStatus());
            item.setPrice(orderItemVo.getPrice());
            orderItems.add(item);
        });

        orderItemService.saveBatch(orderItems);


        return true;
    }

    @PostMapping("save")
    public Boolean saveOrder(@RequestBody OrderVo orderVo){
        System.out.println("========"+JSONUtil.toJsonStr(orderVo));
        Order order = new Order();
        order.setOrderId(orderVo.getOrderId());
        order.setUserId(orderVo.getUserId());
        order.setAddressId(orderVo.getAddressId());
        order.setStatus(orderVo.getStatus());
        boolean save = orderService.save(order);
        if(save){
            System.out.println("========"+JSONUtil.toJsonStr(order));
        }


        return true;
    }
}
