package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;

public interface IConversationDTO {
    UUID getRoomId();
    String getUserId();
    String getUsername();
}