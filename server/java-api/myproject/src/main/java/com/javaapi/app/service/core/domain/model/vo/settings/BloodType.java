package com.javaapi.app.service.core.domain.model.vo.settings;

public enum BloodType {

    OTYPE("O-type"),
    ATYPE("A-type"),
    BTYPE("B-type"),
    ABTYPE("AB-type");

    private final String label;

    
    BloodType(String label) {
        this.label = label;
    }

    public String getType() {
        return label;
    }
}
