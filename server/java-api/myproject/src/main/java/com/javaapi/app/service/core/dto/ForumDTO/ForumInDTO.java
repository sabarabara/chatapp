package com.javaapi.app.service.core.dto.ForumDTO;

import java.util.UUID;


public class ForumInDTO{

    private final UUID roomid;
    private final UUID messageid;
    private final String message;
    private final UUID userid;

    public ForumInDTO(UUID roomid, UUID messageid, String message, UUID userid) {
        this.roomid = roomid;
        this.messageid = messageid;
        this.message = message;
        this.userid = userid;
    }

    public UUID getRoomid() {
        return roomid;
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
}