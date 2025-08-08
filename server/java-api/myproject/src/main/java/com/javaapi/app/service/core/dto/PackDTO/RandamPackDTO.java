package com.javaapi.app.service.core.dto.PackDTO;

public class RandamPackDTO {

  private final String userid;  
  private final String username;
  private final String charactertype;

    public RandamPackDTO(String userid, String username, String charactertype) {
        this.userid = userid;
        this.username = username;
        this.charactertype = charactertype;
    }

    public String getUserid() {
        return userid;
    }

    public String getUsername() {
        return username;
    }

    public String getCharactertype() {
        return charactertype;
    }
}