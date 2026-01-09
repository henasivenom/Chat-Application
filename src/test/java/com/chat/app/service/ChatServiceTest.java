package com.chat.app.service;

import com.chat.app.dto.ChatMessageDTO;
import com.chat.app.model.ChatMessage;
import com.chat.app.model.MessageType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatServiceTest {

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        chatService = new ChatService();
    }

    @Test
    void testProcessValidMessage() {
        ChatMessage message = ChatMessage.builder()
                .sender("TestUser")
                .content("Hello, World!")
                .type(MessageType.CHAT)
                .build();

        ChatMessageDTO result = chatService.processMessage(message);

        assertNotNull(result);
        assertEquals("TestUser", result.getSender());
        assertEquals("Hello, World!", result.getContent());
        assertNotNull(result.getTimestamp());
        assertNotNull(result.getColor());
    }

    @Test
    void testProcessMessageWithEmptySender() {
        ChatMessage message = ChatMessage.builder()
                .sender("")
                .content("Test")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            chatService.processMessage(message);
        });
    }

    @Test
    void testProcessMessageWithEmptyContent() {
        ChatMessage message = ChatMessage.builder()
                .sender("TestUser")
                .content("")
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            chatService.processMessage(message);
        });
    }

    @Test
    void testProcessMessageTooLong() {
        String longMessage = "a".repeat(501);
        ChatMessage message = ChatMessage.builder()
                .sender("TestUser")
                .content(longMessage)
                .build();

        assertThrows(IllegalArgumentException.class, () -> {
            chatService.processMessage(message);
        });
    }

    @Test
    void testAddAndGetActiveUsers() {
        chatService.addUser("User1");
        chatService.addUser("User2");

        assertEquals(2, chatService.getActiveUsers().size());
    }

    @Test
    void testRemoveUser() {
        chatService.addUser("User1");
        chatService.addUser("User2");
        chatService.removeUser("User1");

        assertEquals(1, chatService.getActiveUsers().size());
    }

    @Test
    void testGetRecentMessages() {
        for (int i = 0; i < 10; i++) {
            ChatMessage message = ChatMessage.builder()
                    .sender("User" + i)
                    .content("Message " + i)
                    .type(MessageType.CHAT)
                    .build();
            chatService.processMessage(message);
        }

        assertEquals(10, chatService.getRecentMessages(20).size());
        assertEquals(5, chatService.getRecentMessages(5).size());
    }
}
