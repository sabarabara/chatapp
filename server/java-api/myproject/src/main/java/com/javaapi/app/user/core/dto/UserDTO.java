package com.javaapi.app.user.core.dto;

public class UserDTO{

    private final String username;
    private final String email;

    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }
    
    public String getEmail() {
        return email;
    }
}