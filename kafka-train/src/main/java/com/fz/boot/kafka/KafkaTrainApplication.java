package com.fz.boot.kafka;

import com.fz.boot.kafka.producer.DemoProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = {"com.fz.boot.kafka"})
public class KafkaTrainApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaTrainApplication.class, args);

        DemoProducer demoProducer = applicationContext.getBean(DemoProducer.class);
        for (int i = 0; i < 10; i++) {
            demoProducer.send();
        }

    }

}
