package com.javaapi.app.service.usecase.Pack;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javaapi.app.service.core.domain.model.vo.Pack.Advise;
import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IUserProfileRepo;
import com.javaapi.app.service.core.dto.PackDTO.RecievedPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.service.core.entity.UserProfileEntity;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.model.vo.Username;
import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.entity.UserEntity;

@Component
public class RecommendPackFactory {

    private final IUserRepo userRepo;
    private final IUserProfileRepo userProfileRepo;

    public RecommendPackFactory(IUserRepo userRepo, IUserProfileRepo userProfileRepo) {
        this.userRepo = userRepo;
        this.userProfileRepo = userProfileRepo;
    }


    public List<RecommnedPackDTO> createRecommendPacks(List<RecievedPackDTO> recommendedPacks) {
        return recommendedPacks.stream()
                .map(dto -> {

                    Userid validUserid = new Userid(dto.getUserId());
                    System.out.println("🐞RecommendPackFactory.createRecommendPacks() validUserid: " + validUserid.getUserid());

                    //とりあえず前取得,あとで最適化
                    UserEntity userEntity = userRepo.findByUserid(validUserid.getUserid());
                    UserProfileEntity userProfileEntity = userProfileRepo.findByUserId(validUserid.getUserid());


                    String un = userEntity.getUsername();
                    String ct = userProfileEntity.getCharacterType();

                    Username username = new Username(un);
                    CharacterType characterType = CharacterType.valueOf(ct);
                    Advise advise = new Advise(dto.getAdvice());

                    return new RecommnedPackDTO(
                            username.getUsername(),
                            characterType.getType(),
                            advise.getAdvise()
                    );
                })
                .toList();
    }
}
