package com.javaapi.app.service.core.dto.SettingsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsOutDTO {


    @JsonProperty("_userName")
    private final String username;
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
    @JsonProperty("_jobName")
    private final String characterType;

    public SettingsOutDTO(String username,String bloodType, int height, String birthday, String favoriteWeather, String favoriteColor, String dominantHand , String characterType) {
        this.username = username;
        this.bloodType = bloodType;
        this.height = height;
        this.birthday = birthday;
        this.favoriteWeather = favoriteWeather;
        this.favoriteColor = favoriteColor;
        this.dominantHand = dominantHand;
        this.characterType = characterType;
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

    public String getCharacterType() {
        return characterType;
    }
    public String getUsername() {
        return username;
    }

}