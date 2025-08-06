package com.javaapi.app.service.core.domain.model.vo.settings;


public enum CharacterType {

    //ここで決まったら設定

    WARRIOR("Warrior"),
    MAGE("Mage"),
    ROGUE("Rogue");



    private final String type;

    CharacterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}