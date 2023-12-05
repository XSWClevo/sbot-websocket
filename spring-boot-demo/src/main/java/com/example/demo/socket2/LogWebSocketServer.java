package com.example.demo.socket2;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("logWebSocketServer")
@ServerEndpoint("/log")
public class LogWebSocketServer extends AbstractWebsocketServer {

    @OnMessage
    @Override
    public void onMessage(Session session, String message) {
        log.info("【websocket消息】收到客户端发来的消息:{}", message);

        sendMessage(session, "返回给客户端");

    }
}
