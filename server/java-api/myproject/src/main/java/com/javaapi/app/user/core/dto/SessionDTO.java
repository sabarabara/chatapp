package com.javaapi.app.user.core.dto;


public class SessionDTO {
    private final String userId;
    private final String username;
    private final String email;

    public SessionDTO(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

    
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}