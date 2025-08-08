package com.javaapi.app.service.usecase.Pack;

import java.util.List;

import org.springframework.stereotype.Component;

import com.javaapi.app.service.core.domain.model.vo.Pack.Advise;
import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.user.core.domain.model.vo.Username;

@Component
public class RecommendPackFactory {

    public List<RecommnedPackDTO> createRecommendPacks(List<RecommnedPackDTO> recommendedPacks) {
        return recommendedPacks.stream()
                .map(dto -> {
                    Username username = new Username(dto.getUsername());
                    CharacterType characterType = CharacterType.valueOf(dto.getCharactertype().toUpperCase());
                    Advise advise = new Advise(dto.getAdvise());  // ← クラス名とメソッド名を正しく

                    return new RecommnedPackDTO(
                            username.getUsername(),
                            characterType.getType(),
                            advise.getAdvise()
                    );
                })
                .toList();
    }
}
