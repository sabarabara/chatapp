package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;

public class ConversationOutDTO {

    private final UUID messageid;
    private final String message;
    private final UUID userid;
    private final boolean ispersonal;
    private final String username;

    public ConversationOutDTO(UUID messageid, String message, UUID userid, boolean ispersonal, String username) {
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
    public UUID getUserid() {
        return userid;
    }
    public boolean isIspersonal() {
        return ispersonal;
    }
    public String getUsername() {
        return username;
    }
}