package com.javaapi.app.service.usecase.Setting;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IUserProfileRepo;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.core.entity.UserProfileEntity;
import com.javaapi.app.service.framework.choosecharacterType.DecideCharacterType;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;

import jakarta.servlet.http.HttpSession;


@Service
public class SettingUsecase {

    private final SettingFactory settingFactory;
    private final IUserProfileRepo userProfileRepo;
    private final DecideCharacterType characterType;
    private final SessionUsecase sessionUsecase;

    public SettingUsecase(SettingFactory settingFactory ,IUserProfileRepo userProfileRepo, DecideCharacterType characterType, SessionUsecase sessionUsecase) {
        this.settingFactory = settingFactory;
        this.userProfileRepo = userProfileRepo;
        this.characterType = characterType;
        this.sessionUsecase = sessionUsecase;
    }

    public String createSetting(SettingsInDTO settingsInDTO,HttpSession session) {

        //cpplogic
        System.out.println("🐞SettingUsecase.createSetting() called");
        String charaType = characterType.getCharacterType(settingsInDTO);
        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();
        Userid validUserid = new Userid(userid);

        //factory
        UserProfileEntity settingEntity = settingFactory.createInformation(settingsInDTO, charaType,validUserid);
        //repo
        userProfileRepo.save(settingEntity);
        return "OK";
    }



    public SettingsOutDTO readSetting(HttpSession session) {

        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();

        //repo
        Optional<UserProfileEntity> userProfileEntity = userProfileRepo.findById(userid);

        SettingsOutDTO settingsOutDTO = new SettingsOutDTO(
            userProfileEntity.get().getUserId(),
            userProfileEntity.get().getHeight(),
            userProfileEntity.get().getBirthday().toString(),
            userProfileEntity.get().getFavoriteWeather(),
            userProfileEntity.get().getFavoriteColor(),
            userProfileEntity.get().getDominantHand(),
            userProfileEntity.get().getCharacterType()
        );


        return settingsOutDTO;
    }



    public String updateSetting(SettingsInDTO settingsInDTO,HttpSession session) {

        //pylogic
        String charaType = characterType.getCharacterType(settingsInDTO);
        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();
        Userid validUserid = new Userid(userid);

        //factory
        UserProfileEntity settingEntity = settingFactory.createInformation(settingsInDTO, charaType,validUserid);
        //repo
        userProfileRepo.save(settingEntity);
        return "OK";
    }

    public String deleteSetting(HttpSession session) {

        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();

        //repo
        userProfileRepo.deleteById(userid);
        return "OK";

    }
}