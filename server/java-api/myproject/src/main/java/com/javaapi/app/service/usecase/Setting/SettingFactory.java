package com.javaapi.app.service.usecase.Setting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.model.vo.settings.Birthday;
import com.javaapi.app.service.core.domain.model.vo.settings.BloodType;
import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.domain.model.vo.settings.DominantHand;
import com.javaapi.app.service.core.domain.model.vo.settings.FavoriteColor;
import com.javaapi.app.service.core.domain.model.vo.settings.FavoriteWeather;
import com.javaapi.app.service.core.domain.model.vo.settings.Height;
import com.javaapi.app.service.core.domain.model.vo.settings.Setting;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.entity.UserProfileEntity;
import com.javaapi.app.user.core.domain.model.vo.Userid;


@Service
public class SettingFactory {

    public UserProfileEntity createInformation(SettingsInDTO dto,String charaType,Userid userid) {
    Birthday birthday = new Birthday(dto.getBirthday());
    BloodType bloodyType = BloodType.valueOf(dto.getBloodType());
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

    String validUserId = userid.getUserid();

    String validBirthday = setting.getBirthday().getDate();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    LocalDate persedBirthday = LocalDate.parse(validBirthday, formatter);

    String validBloodType = setting.getBloodyType().getType();
    String validCharacterType = setting.getCharacterType().getType();
    String validdominantHand = setting.getDominantHand().getType();
    String validfavoritecolor = setting.getFavoriteColor().getColor();
    String validfavoriteweather = setting.getFavoriteWeather().getType();
    int validheight = setting.getHeight().getHeightInCm();




    UserProfileEntity userProfileEntity = new UserProfileEntity(validUserId,validBloodType,validheight,persedBirthday,validfavoriteweather,validfavoritecolor,validdominantHand,validCharacterType);

    return userProfileEntity;
    }
}