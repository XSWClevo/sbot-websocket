package com.example.demo.iosocket;

import cn.hutool.core.date.DateUtil;
import io.socket.client.IO;
import io.socket.client.Socket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketIOClientLaunch {
    public static void main(String[] args) {
        // 服务端socket.io连接通信地址
        String url = "http://127.0.0.1:8889";
        try {
            IO.Options options = new IO.Options();

            options.transports = new String[]{"websocket"};
            options.reconnectionAttempts = 2;
            // 失败重连的时间间隔
            options.reconnectionDelay = 1000;
            // 连接超时时间(ms)
            options.timeout = 500;
            // userId: 唯一标识 传给服务端存储
            final Socket socket = IO.socket(url + "?userId=1", options);

            socket.on(Socket.EVENT_CONNECT, args1 -> socket.send("hello..."));

            // 自定义事件`connected` -> 接收服务端成功连接消息
            socket.on(SocketConstant.CONNECTION, objects -> {
                int length = objects.length;
                log.info("服务端自定义事件`connected`:" + objects[0].toString());
            });

            // 自定义事件`push_data_event` -> 接收服务端消息
            socket.on(SocketConstant.PUSH_DATA_EVENT, objects -> log.info("服务端自定义`push_data_event`:" + objects[0].toString()));

            // 自定义事件`myBroadcast` -> 接收服务端广播消息
            socket.on(SocketConstant.BROADCAST, objects -> log.info("服务端广播消息：" + objects[0].toString()));

            socket.connect();

            while (true) {
                Thread.sleep(3000);
                // 自定义事件`push_data_event` -> 向服务端发送消息
                socket.emit(SocketConstant.PUSH_DATA_EVENT, "发送数据 " + DateUtil.now());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
