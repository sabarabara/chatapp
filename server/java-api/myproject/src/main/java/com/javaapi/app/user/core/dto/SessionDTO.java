package com.javaapi.app.user.core.dto;

import java.util.UUID;


public class SessionDTO {
    private final UUID userId;
    private final String username;
    private final String email;

    public SessionDTO(UUID userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    
    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}