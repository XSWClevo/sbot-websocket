package com.example.demo.socket2;

import jakarta.websocket.Session;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理页面多开
 */
@Data
public class CustomSession {

    private Session session;

    /**
     * 页面多开的情况下
     */
    private List<Session> sessionList = new ArrayList<>();

    private String user;

    public CustomSession(Session session, String user) {
        this.session = session;
        this.user = user;
        this.sessionList.add(session);
    }
}
