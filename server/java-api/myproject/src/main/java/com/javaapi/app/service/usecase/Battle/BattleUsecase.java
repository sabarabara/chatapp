package com.javaapi.app.service.usecase.Battle;

import java.util.List;

import org.springframework.session.Session;
import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.query.IBattleRepository;
import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;
import com.javaapi.app.user.core.dto.SessionDTO;
import com.javaapi.app.user.usecase.Session.SessionUsecase;

@Service
public class BattleUsecase {

    private final IBattleRepository battleRepository;
    private final BattleUserFactory battleUserFactory;
    private final SessionUsecase sessionUsecase;

    public BattleUsecase(BattleUserFactory battleUserFactory ,SessionUsecase sessionUsecase, IBattleRepository battleRepository) {
        this.battleUserFactory = battleUserFactory;
        this.sessionUsecase = sessionUsecase;
        this.battleRepository = battleRepository;
    }

    public List<BattleDTO> getBattleUserDetails(Session session) {

        SessionDTO sessionDTO = sessionUsecase.getUserSession(session);
        String userId = sessionDTO.getUserId();

        List<BattleDTO> battleDTOs = battleRepository.findOneToOneConversationPartners(userId);
        List<BattleDTO> validbattleDTO = battleUserFactory.createBattleUser(battleDTOs);

        return validbattleDTO;
    }
}