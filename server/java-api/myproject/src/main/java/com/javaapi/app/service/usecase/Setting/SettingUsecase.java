package com.javaapi.app.service.usecase.Setting;
import org.springframework.stereotype.Repository;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.core.entity.SettingEntity;



public class SettingUsecase {

    public SettingUsecase(SettingFactory settingFactory) {
        this.settingFactory = settingFactory;
        
    }

    public String createSetting(SettingsInDTO settingsInDTO) {

        //pylogic
        String charaType = "DEFAULT_CHARACTER_TYPE";
        SettingEntity settingEntity = SettingFactory.createInformation(settingsInDTO, charaType);
        //repo
    }

    public SettingsOutDTO readSetting() {

        //SettingEntity settingEntity = settingRepository.findById("someId");
    }

    public SettingsOutDTO updateSetting(SettingsInDTO settingsInDTO) {

        //pylogic
        String charaType = "DEFAULT_CHARACTER_TYPE";
        SettingEntity settingEntity = SettingFactory.createInformation(settingsInDTO, charaType);
        //repo
    }

    public String deleteSetting() {
        //repo
    }
}