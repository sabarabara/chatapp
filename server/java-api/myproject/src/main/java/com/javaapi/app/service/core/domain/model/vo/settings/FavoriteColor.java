package com.javaapi.app.service.core.domain.model.vo.settings;

public enum FavoriteColor {


    RED("Red"),
    BLUE("Blue"),
    GREEN("Green"),
    YELLOW("Yellow"),
    PURPLE("Purple"),
    BLACK("Black"),
    WHITE("White");


    private final String color;

    FavoriteColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}