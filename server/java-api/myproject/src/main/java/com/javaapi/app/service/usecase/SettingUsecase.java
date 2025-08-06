package com.javaapi.app.service.usecase;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;



public class SettingUsecase {


    public String createSetting(SettingsInDTO settingsInDTO) {
        return "Setting created successfully";
    }

    public SettingsOutDTO readSetting() {
        return new SettingsOutDTO();
    }

    public SettingsOutDTO updateSetting(SettingsInDTO settingsInDTO) {
        return settingsOutDTO;
    }

    public String deleteSetting() {
        return "Setting deleted successfully";
    }
}