package work.twgj.websocketdemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author weijie.zhu
 * @date 2023/11/21 15:23
 */
@Component
@Slf4j
public class StringWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("和客户端建立连接");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 从客户端获取消息
        String receiveMessage = message.getPayload();
        log.info("receiveMessage:{}",receiveMessage);
        // 发送消息给客户端
        session.sendMessage(new TextMessage(fakeAi(receiveMessage)));
        // 关闭连接
//        session.close(CloseStatus.NORMAL);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        super.handleTransportError(session, exception);
        log.error("连接异常",exception);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("和客户端断开连接");
    }

    private static String fakeAi(String input){
        if (input == null || "".equals(input)){
            return "你说什么？没听清";
        }
        return input.replace('你','我')
                .replace("吗","")
                .replace('?','!')
                .replace('？','！');
    }
}
