package com.javaapi.app.service.core.domain.model.vo.settings;

public enum FavoriteWeather {

    SUNNY("Sunny"),
    RAINY("Rainy"),
    CLOUDY("Cloudy");

    private final String weatherType;

    FavoriteWeather(String weatherType) {
        this.weatherType = weatherType;
    }

    public String getType() {
        return weatherType;
    }
}