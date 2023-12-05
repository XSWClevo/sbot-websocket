package com.example.demo.socket2;

import com.example.demo.IdleTimerTask;
import jakarta.websocket.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Timer;

@Slf4j
public abstract class AbstractWebsocketServer implements WebSocketServer {

    @OnOpen
    @Override
    public void onOpen(Session session) {
        this.addOnlineCount();
        WEB_SOCKET_SET.add(this);
        log.info("【websocket消息】连接成功， 目前数量: {}", getOnlineCount());
        try {
            // 启动计时器
            Timer timer = new Timer(true);
            timer.schedule(new IdleTimerTask(session), IDLE_TIMEOUT, IDLE_TIMEOUT);
            // 写一个算法 冒泡排序的

            IDLE_TIMEOUT_MAP.put(this, timer);

            sendMessage(session, "连接成功");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClose
    @Override
    public void onClose() {
        WEB_SOCKET_SET.remove(this);
        subOnlineCount();

        Timer timer = IDLE_TIMEOUT_MAP.remove(this);
        if (null != timer) {
            timer.cancel();
            log.warn("超过一分钟未操作，连接断开");
        }

        log.warn("【websocket消息】连接断开, 当前总数：{}", getOnlineCount());
    }

    @OnMessage
    @Override
    public void onMessage(Session session, String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);

        sendMessage(session, "返回给客户端");
    }


    @OnError
    @Override
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    @Override
    public void sendMessage(String message) {

    }

    @Override
    public void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getOnlineCount() {
        return ONLINE_COUNT.get();
    }

    @Override
    public void addOnlineCount() {
        ONLINE_COUNT.addAndGet(1);
    }

    @Override
    public void subOnlineCount() {
        if (getOnlineCount() > 0) {
            ONLINE_COUNT.decrementAndGet();
        }
    }
}
