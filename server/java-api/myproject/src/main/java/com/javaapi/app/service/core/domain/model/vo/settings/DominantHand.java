package com.javaapi.app.service.core.domain.model.vo.settings;

public enum DominantHand {

    LEFT_HAND("Left Hand"),
    RIGHT_HAND("Right Hand"),
    AMBIDEXTROUS("Ambidextrous");


    private final String handtype;

    DominantHand(String handtype) {
        this.handtype = handtype;
    }

    public String getType() {
        return handtype;
    }

    
}