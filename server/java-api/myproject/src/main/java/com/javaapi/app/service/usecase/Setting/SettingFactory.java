package com.javaapi.app.service.usecase.Setting;

import com.javaapi.app.service.core.domain.model.vo.settings.Birthday;
import com.javaapi.app.service.core.domain.model.vo.settings.BloodyType;
import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.domain.model.vo.settings.DominantHand;
import com.javaapi.app.service.core.domain.model.vo.settings.FavoriteColor;
import com.javaapi.app.service.core.domain.model.vo.settings.FavoriteWeather;
import com.javaapi.app.service.core.domain.model.vo.settings.Height;
import com.javaapi.app.service.core.domain.model.vo.settings.Setting;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.entity.SettingEntity;


public class SettingFactory {

    public static SettingEntity createInformation(SettingsInDTO dto,
                                                  String charaType) {
    Birthday birthday = new Birthday(dto.getBirthday());
    BloodyType bloodyType = BloodyType.valueOf(dto.getBloodType());
    DominantHand dominantHand = DominantHand.valueOf(dto.getDominantHand());
    FavoriteColor favoriteColor = FavoriteColor.valueOf(dto.getFavoriteColor());
    FavoriteWeather favoriteWeather = FavoriteWeather.valueOf(dto.getFavoriteWeather());
    Height height = new Height(dto.getHeight());

    //これはI/Oによって変更される
    CharacterType characterType = CharacterType.valueOf(charaType);



    Setting setting = new Setting(
            birthday,
            bloodyType,
            characterType,
            dominantHand,
            favoriteColor,
            favoriteWeather,
            height
    );

    SettingEntity settingEntity = new SettingEntity(setting);
    return settingEntity;
    }
}