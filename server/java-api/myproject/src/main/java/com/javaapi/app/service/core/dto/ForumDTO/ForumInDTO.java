package com.javaapi.app.service.core.dto.ForumDTO;

import java.util.UUID;


public class ForumInDTO{

    private final UUID roomid;
    private final String message;
    private final UUID userid;

    public ForumInDTO(UUID roomid, String message, UUID userid) {
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

    public UUID getUserid() {
        return userid;
    }
}