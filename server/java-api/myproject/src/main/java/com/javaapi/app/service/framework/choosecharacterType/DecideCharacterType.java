package com.javaapi.app.service.framework.choosecharacterType;

import java.util.Optional;

import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;


public class DecideCharacterType {

    private final WebClient webClient = WebClient.create();
    String baseUrl = "http://localhost:8080/pack";

    public  String getCharacterType(SettingsInDTO settingsInDTO) {

        Optional<SettingsOutDTO> resCharacterType = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path(baseUrl+"/characterType")
                .build()
            )
            .retrieve()
            .bodyToMono(SettingsOutDTO.class)
            .blockOptional();

            return resCharacterType.get().getCharacterType();
    }
}