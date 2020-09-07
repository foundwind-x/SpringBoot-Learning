package com.fz.boot.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 逻辑表：t_order_item
 * @Author fangzheng
 * @Date 2020/9/4 10:03
 */
@Data
@TableName("t_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -8785161103077691159L;

    private Long orderId;

    private Integer userId;

    private Long addressId;

    private String status;
}
