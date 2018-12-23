package com.example.demo1.socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: xieshaojun
 * @Date: 2018-12-06 20:54
 * @Version 1.0
 */
@ServerEndpoint(value = "/websocket")
@Component
public class HelloSocket {
    Logger logger = LoggerFactory.getLogger(HelloSocket.class);
    /*记录当前用户连接的数量*/
    private static int onlineCount = 0;
    /*concurrent包的线程安全Set,*/
    private static CopyOnWriteArraySet<HelloSocket> helloSockets = new CopyOnWriteArraySet<>();
    /*与某个客户端的连接回话，需要通过他来给客户端发送数据*/
    private Session session;

    /**
     * 连接建立成功调用的方法
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        logger.debug("socket 建立成功");
        this.session = session;
        helloSockets.add(this);
        addOnlineCount();
        logger.debug("有人加入连接。当前人数为：" + getOnlineCount());
    }

    /**
     * 关闭连接
     */
    @OnClose
    public void onClose() {
        helloSockets.remove(this);
        subOnlineCount();
        logger.debug("人数减少,当前为：" + getOnlineCount());
    }

    /**
     * 接收到数据的方法
     *
     * @param message 信息
     * @param session 客户端
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        for (HelloSocket i : helloSockets) {
            if (session.getId().equals(i.session.getId()))
                i.sendMessage("反弹数据：" + message);
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送信息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public static void sendInfo(String message) {
        for (HelloSocket helloSocket : helloSockets) {
            try {
                helloSocket.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }

    }

    /**
     * 获取当前的连接数量
     *
     * @return
     */
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    /**
     * 添加连接数量
     */
    public static synchronized void addOnlineCount() {
        HelloSocket.onlineCount++;
    }

    /**
     * 连接数量减少
     */
    public static synchronized void subOnlineCount() {
        HelloSocket.onlineCount--;
    }

}