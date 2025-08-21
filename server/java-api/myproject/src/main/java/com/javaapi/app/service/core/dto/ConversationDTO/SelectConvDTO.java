package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;


public class SelectConvDTO{

    private final UUID roomid;
    private final String username;

    public SelectConvDTO(UUID roomid, String username) {
        this.roomid = roomid;
        this.username = username;
    }

    public UUID getRoomid() {
        return roomid;
    }

    public String getUsername() {
        return username;
    }
}