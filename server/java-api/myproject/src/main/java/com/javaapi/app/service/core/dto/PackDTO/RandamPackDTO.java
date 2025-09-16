package com.javaapi.app.service.core.dto.PackDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RandamPackDTO {

    @JsonProperty("_randomUserName")
    private final String username;
    @JsonProperty("_randomCharacterType")
    private final String charactertype;

    public RandamPackDTO(String username, String charactertype) {
        this.username = username;
        this.charactertype = charactertype;
    }

    public String getUsername() {
        return username;
    }

    public String getCharactertype() {
        return charactertype;
    }
}