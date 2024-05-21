package RouteC.farm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSocket
@SuppressWarnings("null")
public class WebSocketConfig implements WebSocketConfigurer{

    private final WebSocketHandler webSocketHandler;

    // ws://localhost:8080/chat
    // WebSocket 활성화
    // "*" 을 사용하여 모든 도메인 접속 허용
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
        registry.addHandler(webSocketHandler, "/chat").setAllowedOriginPatterns("*"); // origins 대신 originPatterns 사용
    }
}
