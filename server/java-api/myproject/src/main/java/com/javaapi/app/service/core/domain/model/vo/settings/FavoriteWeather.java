package com.javaapi.app.service.core.domain.model.vo.settings;

public enum FavoriteWeather {

    sunny("Sunny"),
    rainy("Rainy"),
    cloudy("Cloudy");

    private final String weatherType;

    FavoriteWeather(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getType() {
        return weatherType;
    }
}