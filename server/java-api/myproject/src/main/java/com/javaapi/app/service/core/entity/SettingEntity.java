package com.javaapi.app.service.core.entity;

import com.javaapi.app.service.core.domain.model.vo.settings.Setting;


public class SettingEntity{

    private final Setting setting;

    public SettingEntity(Setting setting) {
        this.setting = setting;
    }

    public Setting getSetting() {
        return setting;
    }
}