package com.fz.boot.schedule.conf;

import com.fz.boot.schedule.service.ServiceA;
import com.fz.boot.schedule.service.ServiceB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author fangzheng
 * @description 定时任务配置类
 * spring默认是以单线程执行任务调度,多个任务会交替执行，必须等一个任务执行完了才会执行下个任务
 * 添加@EnableAsync开启对异步的支持，任务上添加@Async注解，表示该定时任务是异步执行的。
 */
@Slf4j
@Component
@EnableAsync
public class ScheduleConf {
    @Autowired
    ServiceA serviceA;
    @Autowired
    ServiceB serviceB;

    /**
     * 注解@Async表示异步执行任务
     * 注解@Scheduled三种配置执行时间的方式：cron,fixedRate,fixedDelay
     * cron：cron表达式,例如："0/1 * * * * ?"；
     * fixedRate：自上一次执行时间之后多长时间执行,单位ms；
     * fixedDelay：自上一次执行完毕之后多长时间执行,单位ms；当它与@Async一起使用时，delay效果失效，相当于fixedRate，到时间就会开启下个定时
     */
//    @Scheduled(cron="0 * * * * ?")
    @Scheduled(fixedDelay = 5*1000)
    @Async("executor1")
    public void task1(){
        log.info("线程{}开始",Thread.currentThread().getName());
        serviceA.executeMethod();
        log.info("线程{}结束",Thread.currentThread().getName());
    }

    /**
     *
     */
//    @Scheduled(cron="0 * * * * ?")
    @Scheduled(fixedDelay = 5*1000)
    @Async("executor2")
    public void task2(){
        log.info("线程{}开始",Thread.currentThread().getName());
        serviceB.executeMethod();
        log.info("线程{}结束",Thread.currentThread().getName());
    }
}
