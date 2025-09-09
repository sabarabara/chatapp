package com.javaapi.app.service.core.dto.ConversationDTO;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IConversationMemoryDTO{
    String getUserId();
    String getUsername();
    UUID getRoomId();
    UUID getMessageId();
    String getMessageContent();
    LocalDateTime getCreatedAt();
}