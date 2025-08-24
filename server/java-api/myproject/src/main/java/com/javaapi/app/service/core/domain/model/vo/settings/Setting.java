package com.javaapi.app.service.core.domain.model.vo.settings;

public class Setting {

    private final Birthday birthday;
    private final BloodType bloodType;
    private final CharacterType characterType;
    private final DominantHand dominantHand;
    private final FavoriteColor favoriteColor;
    private final FavoriteWeather favoriteWeather;
    private final Height height;


    public Setting(Birthday birthday,
                   BloodType bloodyType,
                   CharacterType characterType,
                   DominantHand dominantHand,
                   FavoriteColor favoriteColor,
                   FavoriteWeather favoriteWeather,
                   Height height) {
        this.birthday = birthday;
        this.bloodType = bloodyType;
        this.characterType = characterType;
        this.dominantHand = dominantHand;
        this.favoriteColor = favoriteColor;
        this.favoriteWeather = favoriteWeather;
        this.height = height;
    }

    
    public Birthday getBirthday() {
        return birthday;
    }

    public BloodType getBloodyType() {
        return bloodType;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public DominantHand getDominantHand() {
        return dominantHand;
    }

    public FavoriteColor getFavoriteColor() {
        return favoriteColor;
    }

    public FavoriteWeather getFavoriteWeather() {
        return favoriteWeather;
    }

    public Height getHeight() {
        return height;
    }
}
