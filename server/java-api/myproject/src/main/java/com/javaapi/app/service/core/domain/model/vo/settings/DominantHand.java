package com.javaapi.app.service.core.domain.model.vo.settings;

public enum DominantHand {

    left("left"),
    right("right"),
    ambidextrous("ambidextrous");


    private final String handtype;

    DominantHand(String handtype) {
        this.handtype = handtype;
    }

    public String getType() {
        return handtype;
    }

    
}