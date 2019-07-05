package com.example.demo1.config;

import com.example.demo1.tools.push.RedisSub;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * @Author: xieshaojun
 * @Date: 2018-12-21 22:18
 * @Version 1.0
 */
@Configuration
public class RedisConfig {
    /**
     * Redis 发布订阅
     *
     * @param connectionFactory
     * @param listenerAdapter
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫pub 的通道
        container.addMessageListener(listenerAdapter, new PatternTopic("ts"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    @Bean
    public MessageListenerAdapter listenerAdapter(RedisSub redisSub) {
        //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
        return new MessageListenerAdapter(redisSub, "ts");
    }
}
