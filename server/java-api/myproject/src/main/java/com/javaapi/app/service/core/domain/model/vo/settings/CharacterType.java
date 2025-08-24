package com.javaapi.app.service.core.domain.model.vo.settings;


public enum CharacterType {

    Swordsman("Swordsman"),
    Mage("Mage"),
    Knight("Knight"),
    Ninja("Ninja"),
    Thief("Thief"),
    Archer("Archer"),
    Clown("Clown"),
    Berserker("Berserker"),
    Bard("Bard"),
    Alchemist("Alchemist"),
    Priest("Priest");

    private final String type;

    CharacterType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}