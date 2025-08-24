package com.javaapi.app.service.core.dto.SettingsDTO;

public class SettingsIn_textDTO {

    private final String bloodType;
    private final int height;
    private final String birthday;
    private final String favoriteWeather;
    private final String favoriteColor;
    private final String dominantHand;
    private final String text;

    public SettingsIn_textDTO(String bloodType, int height, String birthday, String favoriteWeather, String favoriteColor, String dominantHand, String text) {
        this.bloodType = bloodType;
        this.height = height;
        this.birthday = birthday;
        this.favoriteWeather = favoriteWeather;
        this.favoriteColor = favoriteColor;
        this.dominantHand = dominantHand;
        this.text = text;
    }
    public String getBloodType() {
        return bloodType;
    }
    public int getHeight() {
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

    public String getText(){
        return text;
    }

}