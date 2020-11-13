package com.fz.boot.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author fangzheng
 */
@Slf4j
@Service
public class ServiceB {


    public void executeMethod(){
        try {
            for (int i = 0; i < 10; i++) {
                log.info("ServiceB execute method");
                //休眠1s
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
