package com.example.demo1.timing;

import com.example.demo1.tools.JsoupTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 一周推荐列表
 */

@Component
@EnableScheduling
public class WeekRecommendListTime {

    @Autowired
    JsoupTools jsoupTools;
//    @Scheduled(cron = "0 0 12 ? * WED")//每星期三 12点执行
    @Scheduled(cron = "0 0/10 * * * ?")
    public void getDish() throws IOException, InterruptedException {
        this.jsoupTools.getDish();
    }
}
