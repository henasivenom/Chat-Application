package com.chat.app.controller;

import com.chat.app.dto.ChatMessageDTO;
import com.chat.app.dto.UserDTO;
import com.chat.app.entity.MessageEntity;
import com.chat.app.model.ChatMessage;
import com.chat.app.model.MessageType;
import com.chat.app.repository.MessageRepository;
import com.chat.app.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Controller
@Slf4j
public class ChatController {
    
    private final ChatService chatService;
    private final MessageRepository messageRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    public ChatController(ChatService chatService, 
                         MessageRepository messageRepository,
                         SimpMessageSendingOperations messagingTemplate) {
        this.chatService = chatService;
        this.messageRepository = messageRepository;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessageDTO sendMessage(@Payload ChatMessage message) {
        ChatMessageDTO dto = chatService.processMessage(message);
        
        // Save to database
        MessageEntity entity = MessageEntity.builder()
            .sender(dto.getSender())
            .content(dto.getContent())
            .type(dto.getType().toString())
            .timestamp(dto.getTimestamp())
            .color(dto.getColor())
            .build();
        
        messageRepository.save(entity);
        
        return dto;
    }

    @MessageMapping("/addUser")
    @SendTo("/topic/messages")
    public ChatMessageDTO addUser(@Payload ChatMessage message, 
                                  SimpMessageHeaderAccessor headerAccessor) {
        String username = message.getSender();
        
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", username);
        
        chatService.addUser(username);
        
        // Broadcast user list
        messagingTemplate.convertAndSend("/topic/users", chatService.getActiveUsers());
        
        ChatMessageDTO joinMessage = ChatMessageDTO.builder()
            .type(MessageType.JOIN)
            .sender(username)
            .content(username + " joined the chat!")
            .timestamp(LocalDateTime.now())
            .build();
        
        log.info("User {} joined the chat", username);
        
        return joinMessage;
    }

    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public ChatMessageDTO userTyping(@Payload ChatMessage message) {
        return ChatMessageDTO.builder()
            .sender(message.getSender())
            .type(MessageType.TYPING)
            .build();
    }

    @GetMapping("/chat")
    public String chat() {
        return "chat";
    }

    @GetMapping("/api/messages/recent")
    @ResponseBody
    public List<ChatMessageDTO> getRecentMessages() {
        return chatService.getRecentMessages(50);
    }

    @GetMapping("/api/users")
    @ResponseBody
    public Collection<UserDTO> getActiveUsers() {
        return chatService.getActiveUsers();
    }
}
