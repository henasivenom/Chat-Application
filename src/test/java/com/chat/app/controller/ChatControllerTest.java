package com.chat.app.controller;

import com.chat.app.dto.ChatMessageDTO;
import com.chat.app.entity.MessageEntity;
import com.chat.app.model.ChatMessage;
import com.chat.app.model.MessageType;
import com.chat.app.repository.MessageRepository;
import com.chat.app.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private SimpMessageSendingOperations messagingTemplate;

    @Mock
    private SimpMessageHeaderAccessor headerAccessor;

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private ChatController chatController;

    private ChatMessage testMessage;
    private ChatMessageDTO testMessageDTO;

    @BeforeEach
    void setUp() {
        testMessage = ChatMessage.builder()
                .sender("TestUser")
                .content("Test message")
                .type(MessageType.CHAT)
                .build();

        testMessageDTO = ChatMessageDTO.builder()
                .sender("TestUser")
                .content("Test message")
                .type(MessageType.CHAT)
                .timestamp(LocalDateTime.now())
                .color("#FF6B6B")
                .build();
    }

    @Test
    void testSendMessage() {
        when(chatService.processMessage(any(ChatMessage.class))).thenReturn(testMessageDTO);
        when(messageRepository.save(any(MessageEntity.class))).thenReturn(new MessageEntity());

        ChatMessageDTO result = chatController.sendMessage(testMessage);

        assertNotNull(result);
        assertEquals("TestUser", result.getSender());
        assertEquals("Test message", result.getContent());
        verify(chatService, times(1)).processMessage(any(ChatMessage.class));
        verify(messageRepository, times(1)).save(any(MessageEntity.class));
    }

    @Test
    void testAddUser() {
        Map<String, Object> sessionAttributes = new HashMap<>();
        when(headerAccessor.getSessionAttributes()).thenReturn(sessionAttributes);
        doNothing().when(chatService).addUser(anyString());

        ChatMessageDTO result = chatController.addUser(testMessage, headerAccessor);

        assertNotNull(result);
        assertEquals(MessageType.JOIN, result.getType());
        assertTrue(result.getContent().contains("joined the chat"));
        verify(chatService, times(1)).addUser("TestUser");
    }
}
