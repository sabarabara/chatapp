package com.javaapi.app.service.core.dto.PackDTO;

public class RandamPackDTO {

  private final String username;
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