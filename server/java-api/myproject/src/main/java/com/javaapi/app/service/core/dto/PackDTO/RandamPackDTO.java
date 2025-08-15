package com.javaapi.app.service.core.dto.PackDTO;

import java.util.UUID;

public class RandamPackDTO {

  private final UUID userid;  
  private final String username;
  private final String charactertype;

    public RandamPackDTO(UUID userid, String username, String charactertype) {
        this.userid = userid;
        this.username = username;
        this.charactertype = charactertype;
    }

    public UUID getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getCharactertype() {
        return charactertype;
    }
}