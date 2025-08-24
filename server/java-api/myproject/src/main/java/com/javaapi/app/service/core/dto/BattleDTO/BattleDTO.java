package com.javaapi.app.service.core.dto.BattleDTO;

public class BattleDTO {

    private final String Username;
    private final String CharacterType;

    public BattleDTO(String username, String characterType) {
        this.Username = username;
        this.CharacterType = characterType;
    }

    public String getUsername() {
        return Username;
    }

    public String getCharacterType() {
        return CharacterType;
    }
}