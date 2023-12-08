package com.example.demo.iosocket;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SplSearchNamespaceHandler {
    //测试使用
    @OnEvent("message")
    public void testHandler(SocketIOClient client, String data, AckRequest ackRequest) throws JsonProcessingException {

        log.info("SplSearch:{}", data);

        if (ackRequest.isAckRequested()) {
            //返回给客户端，说我接收到了
            ackRequest.sendAckData("SplSearch", data);
        }

    }
}
