package com.javaapi.app.service.core.domain.model.vo.settings;

public enum BloodType {

    O("O"),
    A("A"),
    B("B"),
    AB("AB");

    private final String label;

    
    BloodType(String label) {
        this.label = label;
    }

    public String getType() {
        return label;
    }
}
