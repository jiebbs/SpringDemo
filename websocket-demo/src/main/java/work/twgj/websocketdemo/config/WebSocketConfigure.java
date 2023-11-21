package work.twgj.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import work.twgj.websocketdemo.handler.StringWebSocketHandler;

/**
 * @author weijie.zhu
 * @date 2023/11/21 15:38
 */
@Configuration
@EnableWebSocket
public class WebSocketConfigure implements WebSocketConfigurer {

    /**
     * 当客户端通过/connecturl和服务端连接通信时，使用StringWebSocketHandler处理会话。withSockJS的含义是，通信的客户端是通过SockJS实现的
     * */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new StringWebSocketHandler(),"/connect").withSockJS();
    }
}
