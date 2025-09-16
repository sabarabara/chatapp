package com.javaapi.app.service.core.dto.PackDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RecommnedPackDTO{

  @JsonProperty("_recommendUserName")
  private final String username;
  @JsonProperty("_recommendCharacterType")
  private final String charactertype;
  @JsonProperty("_advice")
  private final String advise;

  public RecommnedPackDTO(String username, String charactertype, String advise) {
    this.username = username;
    this.charactertype = charactertype;
    this.advise = advise;
  }

  public String getUsername() {
    return username;
  }
  
  public String getCharactertype() {
    return charactertype;
  }

  public String getAdvise() {
    return advise;
  }
}