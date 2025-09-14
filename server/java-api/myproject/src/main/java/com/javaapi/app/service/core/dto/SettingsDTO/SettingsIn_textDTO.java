package com.javaapi.app.service.core.dto.SettingsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsIn_textDTO {

    @JsonProperty("_userName")
    private final String username;
    @JsonProperty("_jobName")
    private final String characterType;
    @JsonProperty("_bloodType")
    private final String bloodType;
    @JsonProperty("_height")
    private final int height;
    @JsonProperty("_birthday")
    private final String birthday;
    @JsonProperty("_favoriteWeather")
    private final String favoriteWeather;
    @JsonProperty("_favoriteColor")
    private final String favoriteColor;
    @JsonProperty("_dominantHand")
    private final String dominantHand;
    @JsonProperty("_text")
    private final String text;

    public SettingsIn_textDTO(String username, String characterType, String bloodType, int height, String birthday, String favoriteWeather, String favoriteColor, String dominantHand, String text) {
        this.username = username;
        this.characterType = characterType;
        this.bloodType = bloodType;
        this.height = height;
        this.birthday = birthday;
        this.favoriteWeather = favoriteWeather;
        this.favoriteColor = favoriteColor;
        this.dominantHand = dominantHand;
        this.text = text;
    }
    public String getUsername() {
        return username;
    }
    public String getCharacterType() {
        return characterType;
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