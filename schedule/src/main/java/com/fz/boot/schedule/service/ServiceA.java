package com.fz.boot.schedule.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author fangzheng
 */
@Slf4j
@Service
public class ServiceA {

    private ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 3
            , TimeUnit.MINUTES, new LinkedBlockingQueue<>(1024), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardPolicy());

    /*public void executeMethod(){
        try {
            for (int i = 0; i < 10; i++) {
                log.info("ServiceA execute method");
                //休眠1s
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/

    public void executeMethod(){
        int count = 10;
        CountDownLatch latch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            pool.execute(new NewThread(latch,i));
        }
        /**
         * 此处不能调用线程的关闭方法pool.shutdown();
         *         因为外层定时任务复用了一个线程，这样就共用了当前类的成员变量pool，所以不能关闭，否则下次定时任务进来就不能忘线程池里面放任务了
         */
        //pool.shutdown();

        try {
            //挂起主线程等待子线程执行完成
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class NewThread extends Thread{

    private int i;
    private CountDownLatch latch;
    public NewThread(CountDownLatch latch,int i){
        this.i=i;
        this.latch=latch;
    }

    @Override
    public void run() {
        //休眠1s
        try {
            System.out.println(Thread.currentThread().getName()+":ServiceA execute method:"+i);
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            latch.countDown();
        }
    }
}