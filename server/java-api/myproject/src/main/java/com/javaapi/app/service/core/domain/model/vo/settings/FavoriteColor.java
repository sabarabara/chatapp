package com.javaapi.app.service.core.domain.model.vo.settings;

public enum FavoriteColor {


    red("Red"),
    blue("Blue"),
    green("Green"),
    yellow("Yellow"),
    purple("Purple"),
    black("Black"),
    white("White");


    private final String color;

    FavoriteColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}