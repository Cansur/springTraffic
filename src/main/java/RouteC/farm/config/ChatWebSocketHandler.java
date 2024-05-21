package RouteC.farm.config;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
@SuppressWarnings("null")
public class ChatWebSocketHandler extends TextWebSocketHandler{

    // 세션 여러개 분할 필요.

    // CLIENTS 변수에 session 담기
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();

    // 연결 확인
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("id :" + session.getId() + " 님이 연결되었습니다.");
        CLIENTS.put(session.getId(), session);
    }

    // 연결 종료
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("id :" + session.getId() + " 님이 연결 종료되었습니다.");
        CLIENTS.remove(session.getId());
    }

    // 메시지가 보내지면 함수 호출
    // ChatMessageDto 양식을 사용하여 송신 및 수신
    // 메모리 상 채팅방 세션이 없을 경우 생성 시스템 필요
    // restapi를 사용하여 api 구성 제작 필요
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String id = session.getId();  //메시지를 보낸 아이디
        CLIENTS.entrySet().forEach( arg->{
            if(!arg.getKey().equals(id)) {  //같은 아이디가 아니면 메시지를 전달합니다.
                try {
                    arg.getValue().sendMessage(message); // 다른 아이디의 사용자에게 메시지 전달
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
