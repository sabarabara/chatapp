package com.javaapi.app.service.core.dto.ForumDTO;

import java.util.UUID;

public class SelectForumDTO{

    private final UUID roomid;
    private final String title;
    private final String username;

    public SelectForumDTO(UUID roomid, String title, String username) {
        this.roomid = roomid;
        this.title = title;
        this.username = username;
    }

    public UUID getRoomid() {
        return roomid;
    }

    public String getTitle() {
        return title;
    }

    public String getUsername() {
        return username;
    }
}