package com.javaapi.app.service.usecase.Pack;

import java.util.List;

import com.javaapi.app.service.core.domain.model.vo.settings.CharacterType;
import com.javaapi.app.service.core.dto.PackDTO.IRandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RandamPackDTO;
import com.javaapi.app.user.core.domain.model.vo.Userid;
import com.javaapi.app.user.core.domain.model.vo.Username;

public class RandamPackFactory {

    public List<RandamPackDTO> createRandamPacks(List<IRandamPackDTO> packDTOList) {

        return packDTOList.stream()
            .map(dto -> {
                Userid userid = new Userid(dto.getUserId());
                Username username = new Username(dto.getUsername());
                CharacterType characterType = CharacterType.valueOf(dto.getCharacterType());

                return new RandamPackDTO(
                    userid.getUserid(),
                    username.getUsername(),
                    characterType.name()
                );
            })
            .toList();  
    }
}