package com.fz.boot.kafka.producer;

import cn.hutool.json.JSONUtil;
import com.fz.boot.kafka.common.DemoDTO;
import com.fz.boot.kafka.common.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName DemoProducer
 * @Description demo producer
 * @Author fangzheng
 * @Date 2020/8/19 14:48
 * @Version V1.0
 */
@Service
@Slf4j
public class DemoProducer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(){
        DemoDTO demoDTO = DemoDTO.builder().userId(1).name("John").nickName("batman").build();
        String message = JSONUtil.toJsonStr(demoDTO);
        kafkaTemplate.send(KafkaConstant.DEMO_TOPIC , message);
    }
}
