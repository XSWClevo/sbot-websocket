package com.example.demo;

import jakarta.websocket.CloseReason;
import jakarta.websocket.Session;

import java.io.IOException;
import java.util.TimerTask;

public class IdleTimerTask extends TimerTask {
    private final Session session;

    public IdleTimerTask(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        try {
            // 关闭连接
            this.session.close(new CloseReason(CloseReason.CloseCodes.GOING_AWAY, "Idle timeout reached"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
