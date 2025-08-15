package com.javaapi.app.service.framework.choosecharacterType;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;

@Service
public class DecideCharacterType {

    private final WebClient webClient = WebClient.builder()
        .baseUrl("http://localhost:5000")
        .build();

    public String getCharacterType(SettingsInDTO settingsInDTO) {
        String charactertype = webClient.post()
                .uri("/characterType")
                .bodyValue(settingsInDTO)
                .retrieve()
                .bodyToMono(String.class) // ← DTO ではなく String
                .block();

        if (charactertype == null) {
            throw new RuntimeException("Character type not found");
        }

        return charactertype;
    }
}