package com.javaapi.app.service.core.dto.HomeDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HomeDTO {

    @JsonProperty("_userName")
    private final String username;
    @JsonProperty("_jobName")
    private final String charactertype;
    @JsonProperty("_gachaStoneAmount")
    private final int gachaStoneAmount;

    public HomeDTO(String username, String charactertype, int gachaStoneAmount) {
        this.username = username;
        this.charactertype = charactertype;
        this.gachaStoneAmount = gachaStoneAmount;
    }

    public String getUsername() {
        return username;
    }

    public String getCharactertype() {
        return charactertype;
    }

    public int getGachaStoneAmount() {
        return gachaStoneAmount;
    }
}
