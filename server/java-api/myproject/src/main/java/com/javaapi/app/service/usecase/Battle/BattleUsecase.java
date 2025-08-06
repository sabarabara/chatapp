package com.javaapi.app.service.usecase.Battle;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;

@Service
public class BattleUsecase {

    private final BattleUserFactory battleUserFactory;

    public BattleUsecase(BattleUserFactory battleUserFactory) {
        this.battleUserFactory = battleUserFactory;
    }

    public List<BattleDTO> getBattleUserDetails() {

        return battleUserFactory.createBattleUser(
            List.of(
                new BattleDTO("user1", "WARRIOR"),
                new BattleDTO("user2", "MAGE")
            )
        );
    }
}