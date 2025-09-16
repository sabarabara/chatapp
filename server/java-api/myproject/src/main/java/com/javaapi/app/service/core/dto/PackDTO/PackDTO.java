package com.javaapi.app.service.core.dto.PackDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PackDTO {
    @JsonProperty("_gachaStoneAmount")
    private final int gachaStoneAmount;

    public PackDTO(int gachaStoneAmount) {
        this.gachaStoneAmount = gachaStoneAmount;
    }

    public int getGachaStoneAmount() {
        return gachaStoneAmount;
    }
}