package com.example.demo1.timing;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

//@Component
//@Configuration
//@EnableScheduling
public class TestTime {
    @Scheduled(cron = "0/15 * * * * ?")
    public void test(){
        System.out.println("打印开始");
        new Thread(){
            @Override
            public void run() {
                System.out.println("停止开始");
                try {
                    sleep(10000);
                    System.out.println("停止结束（3秒）");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        String s = new String("a");
        System.out.println(s.hashCode());
        System.out.println(s.hashCode());
        s="c";
        System.out.println(s.hashCode());
        System.out.println("打印结束");
    }
}
