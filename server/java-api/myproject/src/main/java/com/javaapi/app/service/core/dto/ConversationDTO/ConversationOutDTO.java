package com.javaapi.app.service.core.dto.ConversationDTO;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConversationOutDTO {

    @JsonProperty("_messageID")
    private final UUID messageid;
    @JsonProperty("_message")
    private final String message;
    @JsonProperty("_imageURL")
    private final String url;
    @JsonProperty("_isPerson")
    private final boolean ispersonal;
    @JsonProperty("_userName")
    private final String username;

    public ConversationOutDTO(UUID messageid, String message, String url, boolean ispersonal, String username) {
        this.messageid = messageid;
        this.message = message;
        this.url = url;
        this.ispersonal = ispersonal;
        this.username = username;
    }

    public UUID getMessageid() {
        return messageid;
    }
    public String getMessage() {
        return message;
    }
    public String getUrl() {
        return url;
    }
    public boolean isIspersonal() {
        return ispersonal;
    }
    public String getUsername() {
        return username;
    }
}