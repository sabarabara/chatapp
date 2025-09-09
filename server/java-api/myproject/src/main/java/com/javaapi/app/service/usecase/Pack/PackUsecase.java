package com.javaapi.app.service.usecase.Pack;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.query.IPackRepo;
import com.javaapi.app.service.core.dto.PackDTO.IRandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RecievedPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.service.framework.packresult.RecommendPackResult;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;

import jakarta.servlet.http.HttpSession;


@Service
public class PackUsecase {

  private final RecommendPackResult packResult;
  private final SessionUsecase sessionUsecase;
  private final IPackRepo packRepo;
  private final RandamPackFactory randamPackFactory;
  private final RecommendPackFactory recommendPackFactory;

  private final int PACK_COUNT = 5;
  int page = 0;
  Pageable pageable = PageRequest.of(page, PACK_COUNT);

  public PackUsecase(RecommendPackResult packResult, SessionUsecase sessionUsecase,IPackRepo packRepo,RandamPackFactory randamPackFactory, RecommendPackFactory recommendPackFactory) {
    this.packResult = packResult;
    this.sessionUsecase = sessionUsecase;
    this.packRepo = packRepo;
    this.randamPackFactory = randamPackFactory;
    this.recommendPackFactory = recommendPackFactory;
  }


    public List<RandamPackDTO> getRandamPack(HttpSession session) {
      SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
      String userId = sessionDTO.getUserId();
      Userid validUserId = new Userid(userId);

      List<IRandamPackDTO> packDTOs = packRepo.findUnmatchedUsers(userId, pageable);
      List<RandamPackDTO> randamPacks = randamPackFactory.createRandamPacks(packDTOs, validUserId);

      return randamPacks;
    }





    public List<RecommnedPackDTO> getRecommendPack(HttpSession session) {
        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userId = sessionDTO.getUserId();
        Userid validUserId = new Userid(userId);

        List<RecievedPackDTO> recommendedPacks = packResult.getRecommendedPack(validUserId);
        List<RecommnedPackDTO> validRecommendedPacks = recommendPackFactory.createRecommendPacks(recommendedPacks, validUserId);
        return validRecommendedPacks;
    }
}