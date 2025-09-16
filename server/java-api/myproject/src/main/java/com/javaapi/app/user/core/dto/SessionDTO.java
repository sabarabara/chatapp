package com.javaapi.app.user.core.dto;


public class SessionDTO {
    private  String userId;
    private  String username;
    private  String email;



    public SessionDTO(String userId, String username, String email) {
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
    public SessionDTO() {}


    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String setUserid(String userId) {
        return userId;
    }
    public String setUsername(String username) {
        return username;
    }
    public String setEmail(String email) {
        return email;
    }
}