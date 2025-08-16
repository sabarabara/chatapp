package com.javaapi.app.service.usecase.Pack;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.dto.PackDTO.IRandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RandamPackDTO;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.model.vo.Username;


@Service
public class RandamPackFactory {

    private final LinkUserService linkUserService;

    public RandamPackFactory(LinkUserService linkUserService) {
        this.linkUserService = linkUserService;
    }


    public List<RandamPackDTO> createRandamPacks(List<IRandamPackDTO> packDTOList, Userid userId) {

        return packDTOList.stream()
            .map(dto -> {
                // ユーザーのルームを作成するのだ
                linkUserService.linkUsersInNewRoom(dto.getUserId(), userId.getUserid());

                Username username = new Username(dto.getUsername());
                CharacterType characterType = CharacterType.valueOf(dto.getCharacterType());

                return new RandamPackDTO(
                    username.getUsername(),
                    characterType.name()
                );
            })
            .toList();
    }
}