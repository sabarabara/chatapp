package com.javaapi.app.service.core.dto.ForumDTO;

import java.util.UUID;

public class ForumOutDTO {

    private final UUID messageid;
    private final String message;
    private final String userid;
    private final boolean ispersonal;
    private final String username;

    public ForumOutDTO(UUID messageid, String message, String userid, boolean ispersonal, String username) {
        this.messageid = messageid;
        this.message = message;
        this.userid = userid;
        this.ispersonal = ispersonal;
        this.username = username;
    }

    public UUID getMessageid() {
        return messageid;
    }
    public String getMessage() {
        return message;
    }
    public String getUserid() {
        return userid;
    }
    public boolean isIspersonal() {
        return ispersonal;
    }
    public String getUsername() {
        return username;
    }
}