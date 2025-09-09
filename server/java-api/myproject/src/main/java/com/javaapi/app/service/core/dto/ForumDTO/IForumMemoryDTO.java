package com.javaapi.app.service.core.dto.ForumDTO;
import java.time.LocalDateTime;
import java.util.UUID;

public interface IForumMemoryDTO {
    String getUserId();
    String getUsername();
    UUID getRoomId();
    UUID getMessageId();
    String getMessageContent();
    LocalDateTime getCreatedAt();
}