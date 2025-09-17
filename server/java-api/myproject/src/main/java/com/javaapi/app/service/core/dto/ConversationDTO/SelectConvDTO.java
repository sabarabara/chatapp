package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;


public class SelectConvDTO{

    @JsonProperty("_roomid")
    private final UUID roomid;
    @JsonProperty("_userName")
    private final String username;
    @JsonProperty("_imageURL")
    private String imageurl;

    public SelectConvDTO(UUID roomid, String username , String imageurl) {
        this.roomid = roomid;
        this.username = username;
        this.imageurl = imageurl;
    }

    public String getImageurl() {
        return imageurl;
    }

    public UUID getRoomid() {
        return roomid;
    }

    public String getUsername() {
        return username;
    }
}