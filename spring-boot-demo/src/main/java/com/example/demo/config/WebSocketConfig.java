package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.HandshakeHandler;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Bean
    public ServerEndpointExporter serverEndpoint(){
        ServerEndpointExporter serverEndpointExporter = new ServerEndpointExporter();

        return serverEndpointExporter;
    }

    /**
     * 添加这个Endpoint，这样在网页中就可以通过websocket连接上服务,也就是我们配置websocket的服务地址,并且可以指定是否使用socketjs
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        String defaultWebsocketEndpointPath = "/server/*";
        String defaultSockJsEndpointPath = "/server/*";
        /**
         * 看了下源码，它的实现类是WebMvcStompEndpointRegistry,addEndpoint是添加到WebMvcStompWebSocketEndpointRegistration的集合中，所以可以添加多个端点
         * 1、addEndpoint：将 “serviceName/server/*”路径,注册STOMP协议的端点。这个HTTP URL是供WebSocket或SockJS客户端访问的地址,用户连接了这个端点后就可以进行websocket通讯，支持socketJs
         */
        StompWebSocketEndpointRegistration webSockedEndpoint = registry.addEndpoint(defaultWebsocketEndpointPath);
        StompWebSocketEndpointRegistration sockJsEndpoint = registry.addEndpoint(defaultSockJsEndpointPath);

//        webSockedEndpoint.setHandshakeHandler(new DefaultHandshakeHandler() {
//            @Override
//            protected Principal determineUser(
//                    ServerHttpRequest request,
//                    WebSocketHandler wsHandler,
//                    Map<String, Object> attributes) {
//                // 从request中获取cookie，并进行自定义认证
//                List<String> cookies = request.getHeaders().get("Cookie");
//                if (cookies != null) {
//                    // 在这里进行自定义的Cookie认证逻辑，验证通过则返回Principal
//                    // 例如，可以解析cookie中的用户信息，然后使用User对象构建Principal
//                    // 如果验证失败，返回null，将导致WebSocket握手失败
//                }
//                return null;
//            }
//        });
        /**
         * 2、withSockJS()指定端点使用SockJS协议
         */
        sockJsEndpoint.withSockJS();
        /**
         * 3、setAllowedOrigins("*") 添加允许跨域访问
         */
        webSockedEndpoint.setAllowedOrigins("*");
        sockJsEndpoint.setAllowedOrigins("*");
//        webSockedEndpoint.setHandshakeHandler(handshakeHandler());
//        sockJsEndpoint.setHandshakeHandler(handshakeHandler());
    }

//    @Bean
//    public HandshakeHandler handshakeHandler() {
//        return new DefaultHandshakeHandler() {
//            @Override
//            protected Principal determineUser(
//                    ServerHttpRequest request,
//                    WebSocketHandler wsHandler,
//                    Map<String, Object> attributes) {
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
//                    return (Principal) authentication.getPrincipal();
//                }
//                return null;
//            }
//        };
//    }

}