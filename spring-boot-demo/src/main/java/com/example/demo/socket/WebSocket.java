package com.example.demo.socket;

import com.example.demo.IdleTimerTask;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
//定义websocket服务器端，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址
@ServerEndpoint("/websocket/component/{type}")
@Slf4j
public class WebSocket {

    private static Map<WebSocket, Timer> userTimers = new ConcurrentHashMap<>();
    private static final long IDLE_TIMEOUT = 60000; // 1 minute

    //实例一个session，这个session是websocket的session
    private Session session;

    // 不同页面的websocket连接集合，定时一分钟无操作则关闭
    private static Set<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    private String type;


    //前端请求时一个websocket时
    @OnOpen
    public void onOpen(Session session, @PathParam("type") String type) throws IOException {
        this.session = session;
        this.type = type;
        log.info("当前的类型：{}", this.type);

        webSocketSet.add(this);
        log.info("【websocket消息】连接成功， 目前数量: {}", webSocketSet.size());
        // 启动计时器
        Timer timer = new Timer(true);
        timer.schedule(new IdleTimerTask(session), IDLE_TIMEOUT, IDLE_TIMEOUT);
        // 写一个算法 冒泡排序的

        userTimers.put(this, timer);

    }

    //前端关闭时一个websocket时
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);

        Timer timer = userTimers.remove(this);
        if (timer != null) {
            timer.cancel();
            log.warn("超过一分钟未操作，连接断开");
            log.info("当前剩余连接数量：{}", webSocketSet.size());
        }
        log.warn("【websocket消息】连接断开");
    }

    //前端向后端发送消息
    @OnMessage
    public void onMessage(String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);
        log.info("当前的消息类型：{}", this.type);
        if (this.type.equals("aaa")) {
            // 执行的业务
        }

        sendMessage("返回给客户端");
        Timer timer = userTimers.get(this);
        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = new Timer(true);
            timer.schedule(new IdleTimerTask(this.session), IDLE_TIMEOUT, IDLE_TIMEOUT);
            userTimers.put(this, timer);
        }
    }

    //新增一个方法用于主动向客户端发送消息
    public void sendMessage(String message) {
        log.info("【websocket消息】广播消息, type: {}, message={}", this.type, message);
        try {
            this.session.getBasicRemote().sendText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}