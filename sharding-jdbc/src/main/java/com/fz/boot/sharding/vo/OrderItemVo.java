package com.fz.boot.sharding.vo;

import cn.hutool.json.JSONUtil;
import lombok.Data;

/**
 * @Description
 * @Author fangzheng
 * @Date 2020/9/7 16:35
 */
@Data
public class OrderItemVo {

    private Double price;
    private String description;

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
