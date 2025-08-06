package com.javaapi.app.user.core.dto;


public class SessionDTO {
    private String userId;
    private String username;
    private String email;

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