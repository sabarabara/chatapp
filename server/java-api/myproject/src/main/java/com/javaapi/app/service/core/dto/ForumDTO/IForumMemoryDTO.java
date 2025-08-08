package com.javaapi.app.service.core.dto.ForumDTO;

import java.time.LocalDateTime;

public interface IForumMemoryDTO {
    String getUserId();
    String getUsername();
    String getRoomId();
    String getMessageId();
    String getMessageContent();
    LocalDateTime getCreatedAt();
}