package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;


public class ConversationInDTO{

    private final UUID roomid;
    private final String message;
    private final String userid;

    public ConversationInDTO(UUID roomid, String message, String userid) {
        this.roomid = roomid;
        this.message = message;
        this.userid = userid;
    }

    public UUID getRoomid() {
        return roomid;
    }

    public String getMessage() {
        return message;
    }

    public String getUserid() {
        return userid;
    }
}