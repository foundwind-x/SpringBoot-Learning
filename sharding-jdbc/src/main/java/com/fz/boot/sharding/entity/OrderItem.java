package com.fz.boot.sharding.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 逻辑表：t_order_item
 * @Author fangzheng
 * @Date 2020/9/4 10:54
 */
@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 6106520973563268754L;

    @TableId(type = IdType.AUTO)
    private Long orderItemId;

    private Long orderId;

    private Integer userId;

    private Double price;

    private String status;
}
