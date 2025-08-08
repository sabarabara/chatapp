package com.javaapi.app.service.core.dto.PackDTO;

public class RecommnedPackDTO{

  private final String username;
  private final String charactertype;
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