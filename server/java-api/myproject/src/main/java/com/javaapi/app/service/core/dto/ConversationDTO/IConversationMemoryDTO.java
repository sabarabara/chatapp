package com.javaapi.app.service.core.dto.ConversationDTO;
import java.time.LocalDateTime;

public interface IConversationMemoryDTO{
    String getUserId();
    String getUsername();
    String getRoomId();
    String getMessageId();
    String getMessageContent();
    LocalDateTime getCreatedAt();
}