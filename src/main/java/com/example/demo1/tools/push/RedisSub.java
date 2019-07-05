package com.example.demo1.tools.push;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

/**
 * @Author: xieshaojun
 * @Date: 2018-12-21 21:45
 * @Version 1.0
 */
@Service
public class RedisSub implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println("ok>>>>>" + message);
    }
}
