package com.javaapi.app.service.usecase.Setting;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IUserProfileRepo;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.core.entity.UserProfileEntity;
import com.javaapi.app.service.framework.choosecharacterType.DecideCharacterType;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.core.dto.UserDTO;
import com.javaapi.app.user.core.entity.UserEntity;
import com.javaapi.app.user.usecase.Session.SessionUsecase;
import com.javaapi.app.user.usecase.User.UserFactory;

import jakarta.servlet.http.HttpSession;


@Service
public class SettingUsecase {

    private final SettingFactory settingFactory;
    private final IUserProfileRepo userProfileRepo;
    private final DecideCharacterType characterType;
    private final SessionUsecase sessionUsecase;
    private final UserFactory userFactory;

    public SettingUsecase(SettingFactory settingFactory ,IUserProfileRepo userProfileRepo, DecideCharacterType characterType, SessionUsecase sessionUsecase, UserFactory userFactory) {
        this.settingFactory = settingFactory;
        this.userProfileRepo = userProfileRepo;
        this.characterType = characterType;
        this.sessionUsecase = sessionUsecase;
        this.userFactory = userFactory;
    }

    public String createSetting(SettingsInDTO settingsInDTO,HttpSession session) {

        //cpplogic
        String charaType = characterType.getCharacterType(settingsInDTO);
        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        UUID userid = sessionDTO.getUserId();
        Userid validUserid = new Userid(userid);
        

        //factory
        UserProfileEntity settingEntity = settingFactory.createInformation(settingsInDTO, charaType,validUserid);

        //repo

        //dto作成
        UserDTO userDTO = new UserDTO(
            sessionDTO.getUsername(),
            sessionDTO.getEmail()
        );
        UserEntity userEntity = userFactory.createUser(userDTO);
        settingEntity.setUser(userEntity);

        //repo作成
        userProfileRepo.save(settingEntity);
        return "OK";
    }



    public SettingsOutDTO readSetting(HttpSession session) {

        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        UUID userid = sessionDTO.getUserId();

        //repo
        Optional<UserProfileEntity> userProfileEntity = userProfileRepo.findById(userid);

        SettingsOutDTO settingsOutDTO = new SettingsOutDTO(
            userProfileEntity.get().getBloodType(),
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
        UUID userid = sessionDTO.getUserId();
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
        UUID userid = sessionDTO.getUserId();

        //repo
        userProfileRepo.deleteById(userid);
        return "OK";

    }
}
