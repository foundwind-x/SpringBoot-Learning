package com.fz.boot.sharding.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author fangzheng
 * @Date 2020/9/4 14:09
 */
@Data
public class OrderVo {
    private Long orderId;

    private Integer userId;

    private Long addressId;

    private String status;

    private List<OrderItemVo> orderItemVos;
}
