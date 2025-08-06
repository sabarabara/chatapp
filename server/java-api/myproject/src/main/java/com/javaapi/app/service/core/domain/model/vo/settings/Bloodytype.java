package com.javaapi.app.service.core.domain.model.vo.settings;

public enum BloodyType {

    OTYPE ("O-type"),
    ATYPE ("A-type"),
    BTYPE ("B-type"),
    ABTYPE ("AB-type");

    private final String label;

    BloodyType(String label) {
        this.label = label;
    }

    public String getType() {
        return label;
    }
}