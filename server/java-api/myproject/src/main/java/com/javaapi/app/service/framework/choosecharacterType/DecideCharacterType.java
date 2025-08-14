package com.javaapi.app.service.framework.choosecharacterType;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;

@Service
public class DecideCharacterType {

    // WebClient に baseUrl を設定
    private final WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:5000")
        .build();

    public String getCharacterType(SettingsInDTO settingsInDTO) {

        Optional<SettingsOutDTO> resCharacterType = webClient.post()
            .uri("/characterType")
            .bodyValue(settingsInDTO)
            .retrieve()
            .bodyToMono(SettingsOutDTO.class)
            .blockOptional();
        return resCharacterType
                .orElseThrow(() -> new RuntimeException("Character type not found"))
                .getCharacterType();
    }
}
