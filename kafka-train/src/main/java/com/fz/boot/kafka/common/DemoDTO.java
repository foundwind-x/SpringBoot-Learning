package com.fz.boot.kafka.common;

import lombok.Builder;
import lombok.Data;

/**
 * @Description demo of dto
 * @Author fangzheng
 * @Date 2020/8/14 15:27
 * @Version V1.0
 */
@Data
@Builder
public class DemoDTO {
    private Integer userId;
    private String name;
    private String nickName;
}
