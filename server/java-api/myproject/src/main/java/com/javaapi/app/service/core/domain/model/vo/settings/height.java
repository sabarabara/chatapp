package com.javaapi.app.service.core.domain.model.vo.settings;

public class Height {
    private final int heightInCm;

    public Height(int heightInCm) {
        this.heightInCm = heightInCm;
    }

    public int getHeightInCm() {
        return heightInCm;
    }
}