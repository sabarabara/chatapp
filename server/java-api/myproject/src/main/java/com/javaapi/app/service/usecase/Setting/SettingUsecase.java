package com.javaapi.app.service.usecase.Setting;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.model.vo.settings.SendText;
import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IUserProfileRepo;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsIn_textDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.core.entity.UserProfileEntity;
import com.javaapi.app.service.framework.choosecharacterType.DecideCharacterType;
import com.javaapi.app.service.framework.packresult.RecommendPackResult;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.core.entity.UserEntity;
import com.javaapi.app.user.usecase.Session.SessionUsecase;
import com.javaapi.app.user.usecase.User.UserFactory;



@Service
public class SettingUsecase {

    private final SettingFactory settingFactory;
    private final IUserProfileRepo userProfileRepo;
    private final DecideCharacterType characterType;
    private final SessionUsecase sessionUsecase;
    private final UserFactory userFactory;
    private final RecommendPackResult recommendPackResult;
    private final IUserRepo userRepository;

    public SettingUsecase(SettingFactory settingFactory ,IUserProfileRepo userProfileRepo, DecideCharacterType characterType, SessionUsecase sessionUsecase, UserFactory userFactory, RecommendPackResult recommendPackResult, IUserRepo userRepository) {
        this.settingFactory = settingFactory;
        this.userProfileRepo = userProfileRepo;
        this.characterType = characterType;
        this.sessionUsecase = sessionUsecase;
        this.userFactory = userFactory;
        this.recommendPackResult = recommendPackResult;
        this.userRepository = userRepository;
    }

    public String createSetting(SettingsIn_textDTO settingsIn_textDTO,String session) {

        
        SettingsInDTO settingsInDTO = new SettingsInDTO(
            settingsIn_textDTO.getBloodType(),
            settingsIn_textDTO.getHeight(),
            settingsIn_textDTO.getBirthday(),
            settingsIn_textDTO.getFavoriteWeather(),
            settingsIn_textDTO.getFavoriteColor(),
            settingsIn_textDTO.getDominantHand()
        );


        //cpplogic
        String charaType = characterType.getCharacterType(settingsInDTO);
        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();
        Userid validUserid = new Userid(userid);

        //pylogic
        String text = settingsIn_textDTO.getText();
        SendText sendText = new SendText(text);
        String registerHobbyResult = recommendPackResult.registerHobby(validUserid, sendText.getText());
        System.out.println("🐞SettingUsecase.createSetting() registerHobbyResult: " + registerHobbyResult);
        

        //factory
        UserProfileEntity settingEntity = settingFactory.createInformation(settingsInDTO, charaType,validUserid);

        //repo
        
        UserEntity userEntity = userRepository.findById(validUserid.getUserid())
        .orElseThrow(() -> new IllegalArgumentException("指定されたユーザーは存在しません: " + validUserid.getUserid()));

        settingEntity.setUser(userEntity);

        userProfileRepo.save(settingEntity);
        return "OK";
    }



    public SettingsOutDTO readSetting(String session) {

        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();
        String username = sessionDTO.getUsername();

        //repo
        Optional<UserProfileEntity> userProfileEntity = userProfileRepo.findById(userid);

        SettingsOutDTO settingsOutDTO = new SettingsOutDTO(
            username,
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



    public String updateSetting(SettingsInDTO settingsInDTO,String session) {

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

    public String deleteSetting(String session) {

        //session
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userid = sessionDTO.getUserId();

        //repo
        userProfileRepo.deleteById(userid);
        return "OK";

    }
}
