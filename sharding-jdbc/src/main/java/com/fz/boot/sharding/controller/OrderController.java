package com.fz.boot.sharding.controller;

import cn.hutool.core.bean.BeanUtil;
import com.fz.boot.sharding.entity.Order;
import com.fz.boot.sharding.entity.OrderItem;
import com.fz.boot.sharding.mapper.OrderItemMapper;
import com.fz.boot.sharding.service.OrderItemService;
import com.fz.boot.sharding.service.OrderService;
import com.fz.boot.sharding.vo.OrderItemVo;
import com.fz.boot.sharding.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author fangzheng
 * @Date 2020/9/4 14:01
 */
@Slf4j
@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @PostMapping("saveAll")
    public Boolean save(@RequestBody @Validated OrderVo orderVo){
        System.out.println(orderVo);
        Order order = new Order();
        order.setOrderId(orderVo.getOrderId());
        order.setUserId(orderVo.getUserId());
        order.setAddressId(orderVo.getAddressId());
        order.setStatus(orderVo.getStatus());
        orderService.save(order);

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

        boolean b = orderItemService.saveBatch(orderItems);
        if(b){
            //打印查看通过sharding主键生成器算法snowflake产生的主键是否返回
            log.info("====orderItems====");
            orderItems.forEach(System.out::println);
        }

        return true;
    }

    @PostMapping("save")
    public Boolean saveOrder(@RequestBody @Validated OrderVo orderVo){
        Order order = new Order();
        order.setOrderId(orderVo.getOrderId());
        order.setUserId(orderVo.getUserId());
        order.setAddressId(orderVo.getAddressId());
        order.setStatus(orderVo.getStatus());
        orderService.save(order);

        return true;
    }

    @GetMapping("get")
    public OrderVo getOrder(@RequestParam("userId") Integer userId, @RequestParam("orderId") Long orderId){
        Order order = orderService.lambdaQuery().eq(Order::getUserId,userId).eq(Order::getOrderId,orderId).one();
        OrderVo orderVo = new OrderVo();
        BeanUtil.copyProperties(order,orderVo);
        List<OrderItemVo> orderItemVos = new ArrayList<>();
        orderVo.setOrderItemVos(orderItemVos);
        List<OrderItem> list = orderItemService.lambdaQuery().eq(OrderItem::getUserId,userId).eq(OrderItem::getOrderId, orderId).list();
        list.forEach(orderItem -> {
            OrderItemVo orderItemVo = new OrderItemVo();
            BeanUtil.copyProperties(orderItem,orderItemVo);
            orderItemVos.add(orderItemVo);
        });

        return orderVo;
    }

    @GetMapping("list")
    public List<OrderVo> listOrder(@RequestParam("userId") Integer userId){
        List<OrderVo> orderVos = new ArrayList<>();
        List<Order> orders = orderService.lambdaQuery().eq(Order::getUserId,userId).list();
        List<Long> orderIds = orders.stream().map(Order::getOrderId).collect(Collectors.toList());
        List<OrderItem> orderItems = orderItemService.lambdaQuery().eq(OrderItem::getUserId,userId).in(OrderItem::getOrderId, orderIds).list();
        Map<Long, List<OrderItem>> orderItemMap = orderItems.stream().collect(Collectors.groupingBy(OrderItem::getOrderId));
        orders.forEach(order -> {
            OrderVo orderVo = new OrderVo();
            BeanUtil.copyProperties(order,orderVo);
            List<OrderItemVo> orderItemVos = new ArrayList<>();
            orderVo.setOrderItemVos(orderItemVos);
            List<OrderItem> list = orderItemMap.get(order.getOrderId());
            list.forEach(orderItem -> {
                OrderItemVo orderItemVo = new OrderItemVo();
                BeanUtil.copyProperties(orderItem,orderItemVo);
                orderItemVos.add(orderItemVo);
            });
            orderVos.add(orderVo);
        });

        return orderVos;
    }
}
