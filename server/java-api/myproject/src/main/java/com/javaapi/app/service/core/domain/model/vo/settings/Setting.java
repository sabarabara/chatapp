package com.javaapi.app.service.core.domain.model.vo.settings;

public class Setting {

    private final Birthday birthday;
    private final BloodyType bloodyType;
    private final CharacterType characterType;
    private final DominantHand dominantHand;
    private final FavoriteColor favoriteColor;
    private final FavoriteWeather favoriteWeather;
    private final Height height;


    public Setting(Birthday birthday,
                   BloodyType bloodyType,
                   CharacterType characterType,
                   DominantHand dominantHand,
                   FavoriteColor favoriteColor,
                   FavoriteWeather favoriteWeather,
                   Height height) {
        this.birthday = birthday;
        this.bloodyType = bloodyType;
        this.characterType = characterType;
        this.dominantHand = dominantHand;
        this.favoriteColor = favoriteColor;
        this.favoriteWeather = favoriteWeather;
        this.height = height;
    }

    // getter
    public Birthday getBirthday() {
        return birthday;
    }

    public BloodyType getBloodyType() {
        return bloodyType;
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
