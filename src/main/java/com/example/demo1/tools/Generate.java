package com.example.demo1.tools;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class Generate {
    private static Long randomNumber;
    private static Long curIndex = Long.valueOf(0L);

    public static String generateUUID() {
        //生成唯一id
        String id = UUID.randomUUID().toString();
        //替换uuid中的"-"
        return id.replace("-", "");
    }

    public synchronized static String getId() {
        Long index = null;
        // 从0到999 curIndex*100 curIndex 100-99900
        index = (curIndex = curIndex + 1L) % 1000L;
        if (curIndex >= 1000L) {
            curIndex = 0L;
        }
        // 随机数 1-10
        randomNumber = (long) new Random().nextInt(100);
        return (new Date()).getTime() * 100000L + index * 100L
                + randomNumber+"";
    }
}
