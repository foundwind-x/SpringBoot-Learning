package com.fz.boot.sharding.vo;

import cn.hutool.json.JSONUtil;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Description
 * @Author fangzheng
 * @Date 2020/9/4 14:09
 */
@Data
public class OrderVo {
    @NotNull(message = "orderId is empty")
    private Long orderId;
    @NotNull(message = "userId is empty")
    private Integer userId;

    private Long addressId;

    private String status;

    private List<OrderItemVo> orderItemVos;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
