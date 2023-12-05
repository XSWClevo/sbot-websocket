package com.example.demo.socket2;

import jakarta.websocket.*;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public interface WebSocketServer {
    AtomicInteger ONLINE_COUNT = new AtomicInteger(0);

    CopyOnWriteArraySet<WebSocketServer> WEB_SOCKET_SET = new CopyOnWriteArraySet<>();

    Map<WebSocketServer, Timer> IDLE_TIMEOUT_MAP = new ConcurrentHashMap<>();

    long IDLE_TIMEOUT = 60000; // 1 minute

    // 暂时未用到，打算是使用userId, Session
    Map<String, CustomSession> WEB_SOCKET_MAP = new ConcurrentHashMap<>();

    @OnOpen
    void onOpen(Session session);

    @OnClose
    void onClose();

    @OnMessage
    void onMessage(Session session, String message);

    @OnError
    void onError(Session session, Throwable error);

    void sendMessage(String message);

    void sendMessage(Session session, String message);

    int getOnlineCount();

    void addOnlineCount();

    void subOnlineCount();
}
