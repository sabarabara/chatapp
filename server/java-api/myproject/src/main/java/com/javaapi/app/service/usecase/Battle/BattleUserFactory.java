package com.javaapi.app.service.usecase.Battle;

import java.util.List;

import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;
import com.javaapi.app.user.core.domain.model.vo.Username;

public class BattleUserFactory {

    public BattleUserFactory() {
    }

    public static List<BattleDTO> createBattleUser(List<BattleDTO> battleDTOList) {
        return battleDTOList.stream()
            .map(dto -> {

                Username username = new Username(dto.getUsername());
                CharacterType characterType = CharacterType.valueOf(dto.getCharacterType().toUpperCase());

                return new BattleDTO(
                    username.getUsername(),
                    characterType.name()
                );
            })
            .toList();
    }
}
