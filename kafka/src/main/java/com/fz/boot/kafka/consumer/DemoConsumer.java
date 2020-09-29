package com.fz.boot.kafka.consumer;

import cn.hutool.json.JSONUtil;
import com.fz.boot.kafka.common.DemoDTO;
import com.fz.boot.kafka.common.KafkaConstant;
import com.fz.boot.kafka.common.LogConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName DemoConsumer
 * @Description demo consumer
 * @Author fangzheng
 * @Date 2020/8/19 14:27
 * @Version V1.0
 */
@Service
@Slf4j
public class DemoConsumer {

    @KafkaListener(topics = KafkaConstant.DEMO_TOPIC)
    public void listen(ConsumerRecord<String, String> record, Consumer consumer) {
        Optional<ConsumerRecord<String, String>> recordOptional = Optional.ofNullable(record);
        if(recordOptional.isPresent()){
            String message = recordOptional.get().value();
            DemoDTO demoDTO = JSONUtil.toBean(message, DemoDTO.class);
            log.info(message);
        }else {
            log.info(LogConstant.EMPTY_MESSAGE);
        }

        consumer.commitAsync();
    }
}
