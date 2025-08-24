package com.javaapi.app.service.core.dto.SettingsDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SettingsInDTO {

    @JsonProperty("blood_type")
    private final String bloodType;

    @JsonProperty("height")
    private final int height;

    @JsonProperty("birthday")
    private final String birthday;

    @JsonProperty("favorite_weather")
    private final String favoriteWeather;

    @JsonProperty("favorite_color")
    private final String favoriteColor;

    @JsonProperty("dominant_hand")
    private final String dominantHand;

    public SettingsInDTO(
            @JsonProperty("blood_type") String bloodType,
            @JsonProperty("height") int height,
            @JsonProperty("birthday") String birthday,
            @JsonProperty("favorite_weather") String favoriteWeather,
            @JsonProperty("favorite_color") String favoriteColor,
            @JsonProperty("dominant_hand") String dominantHand) {
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
}
