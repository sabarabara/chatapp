package com.javaapi.app.service.core.dto.SettingsDTO;

public class SettingsInDTO {

    private final String bloodType;
    private final String height;
    private final String birthday;
    private final String favoriteWeather;
    private final String favoriteColor;
    private final String dominantHand;

    public SettingsInDTO(String bloodType, String height, String birthday, String favoriteWeather, String favoriteColor, String dominantHand) {
        this.bloodType = bloodType;
        this.height = height;
        this.birthday = birthday;
        this.favoriteWeather = favoriteWeather;
        this.favoriteColor = favoriteColor;
        this.dominantHand = dominantHand;
    }
    public String getBloodType() {
        return bloodType;
    }
    public String getHeight() {
        return height;
    }
    public String getBirthday() {
        return birthday;
    }
    public String getFavoriteWeather() {
        return favoriteWeather;
    }
    public String getFavoriteColor() {
        return favoriteColor;
    }
    public String getDominantHand() {
        return dominantHand;
    }

}