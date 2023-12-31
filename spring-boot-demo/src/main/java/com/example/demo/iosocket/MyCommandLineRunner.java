package com.example.demo.iosocket;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(value = 1)
public class MyCommandLineRunner implements CommandLineRunner, DisposableBean {

    private final SocketIOServer server;

    private final SplSearchNamespaceHandler splSearchNamespaceHandler;

    @Autowired
    public MyCommandLineRunner(SocketIOServer server, SplSearchNamespaceHandler splSearchNamespaceHandler) {
        this.splSearchNamespaceHandler = splSearchNamespaceHandler;
        this.server = server;
        System.out.println("初始化MyCommandLineRunner");
    }

    @Override
    public void run(String... args) {
        try {
            server.getNamespace("/xsw").addListeners(splSearchNamespaceHandler);
            server.start();
            System.out.println("socket.io启动成功！");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        //如果用kill -9  这个监听是没用的，有可能会导致你服务kill掉了，但是socket服务没有kill掉
        server.stop();
        log.info("SocketIOServer==============================关闭成功");
    }
}