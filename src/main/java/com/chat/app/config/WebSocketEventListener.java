package com.chat.app.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.chat.app.dto.ChatMessageDTO;
import com.chat.app.model.MessageType;

import java.time.LocalDateTime;

@Component
@Slf4j
public class WebSocketEventListener {

    private final SimpMessageSendingOperations messagingTemplate;

    public WebSocketEventListener(SimpMessageSendingOperations messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        log.info("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        
        if (username != null) {
            log.info("User Disconnected: {}", username);
            
            ChatMessageDTO chatMessage = ChatMessageDTO.builder()
                .type(MessageType.LEAVE)
                .sender(username)
                .content(username + " left the chat")
                .timestamp(LocalDateTime.now())
                .build();
            
            messagingTemplate.convertAndSend("/topic/messages", chatMessage);
            messagingTemplate.convertAndSend("/topic/users", username);
        }
    }
}
