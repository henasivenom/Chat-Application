package com.chat.app.dto;

import com.chat.app.model.MessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessageDTO {
    private Long id;
    private String sender;
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;
    private String color;
}
