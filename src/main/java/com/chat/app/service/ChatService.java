package com.chat.app.service;

import com.chat.app.dto.ChatMessageDTO;
import com.chat.app.dto.UserDTO;
import com.chat.app.model.ChatMessage;
import com.chat.app.model.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class ChatService {
    
    private final Map<String, UserDTO> activeUsers = new ConcurrentHashMap<>();
    private final List<ChatMessageDTO> messageHistory = Collections.synchronizedList(new ArrayList<>());
    private final Map<String, String> userColors = new ConcurrentHashMap<>();
    private final String[] COLORS = {
        "#FF6B6B", "#4ECDC4", "#45B7D1", "#FFA07A", "#98D8C8",
        "#F7DC6F", "#BB8FCE", "#85C1E2", "#F8B739", "#52B788"
    };
    
    private static final int MAX_MESSAGE_LENGTH = 500;
    private static final int MAX_HISTORY_SIZE = 100;
    
    public ChatMessageDTO processMessage(ChatMessage message) {
        // Validate message
        validateMessage(message);
        
        // Get or assign color to user
        String color = userColors.computeIfAbsent(message.getSender(), 
            k -> COLORS[userColors.size() % COLORS.length]);
        
        // Create DTO
        ChatMessageDTO dto = ChatMessageDTO.builder()
            .sender(message.getSender())
            .content(sanitizeContent(message.getContent()))
            .type(message.getType() != null ? message.getType() : MessageType.CHAT)
            .timestamp(LocalDateTime.now())
            .color(color)
            .build();
        
        // Store in history
        addToHistory(dto);
        
        log.info("Message processed: {} from {}", dto.getContent(), dto.getSender());
        
        return dto;
    }
    
    public void addUser(String username) {
        String color = userColors.computeIfAbsent(username, 
            k -> COLORS[userColors.size() % COLORS.length]);
        
        UserDTO user = UserDTO.builder()
            .username(username)
            .color(color)
            .online(true)
            .build();
        
        activeUsers.put(username, user);
        log.info("User joined: {}", username);
    }
    
    public void removeUser(String username) {
        activeUsers.remove(username);
        log.info("User left: {}", username);
    }
    
    public Collection<UserDTO> getActiveUsers() {
        return new ArrayList<>(activeUsers.values());
    }
    
    public List<ChatMessageDTO> getRecentMessages(int limit) {
        synchronized (messageHistory) {
            int size = messageHistory.size();
            int fromIndex = Math.max(0, size - limit);
            return new ArrayList<>(messageHistory.subList(fromIndex, size));
        }
    }
    
    private void validateMessage(ChatMessage message) {
        if (message.getSender() == null || message.getSender().trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be empty");
        }
        
        if (message.getContent() == null || message.getContent().trim().isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }
        
        if (message.getContent().length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Message exceeds maximum length of " + MAX_MESSAGE_LENGTH);
        }
    }
    
    private String sanitizeContent(String content) {
        // Basic XSS prevention
        return content.trim()
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("&", "&amp;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;");
    }
    
    private void addToHistory(ChatMessageDTO message) {
        synchronized (messageHistory) {
            messageHistory.add(message);
            // Keep only recent messages
            while (messageHistory.size() > MAX_HISTORY_SIZE) {
                messageHistory.remove(0);
            }
        }
    }
}
