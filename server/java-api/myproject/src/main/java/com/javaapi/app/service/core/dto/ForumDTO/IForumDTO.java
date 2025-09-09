package com.javaapi.app.service.core.dto.ForumDTO;

import java.util.UUID;


public interface IForumDTO {
    String getTitle();
    UUID getRoomId();
    String getUserId();
    String getUsername();
}