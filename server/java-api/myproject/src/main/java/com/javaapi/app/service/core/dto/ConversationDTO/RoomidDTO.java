package com.javaapi.app.service.core.dto.ConversationDTO;
import java.util.UUID;

public class RoomidDTO {
    private final UUID roomid;

    public RoomidDTO(UUID roomid) {
        this.roomid = roomid;
    }

    public UUID getRoomid() {
        return roomid;
    }
}