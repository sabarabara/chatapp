package com.javaapi.app.service.framework.choosecharacterType;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;

@Service
public class DecideCharacterType {

    private final WebClient webClient;

    public DecideCharacterType() {

        String cppHost = System.getenv("CPP_WORKER_HOST");
        if (cppHost == null || cppHost.isEmpty()) {
            cppHost = "cpp-worker:8080"; // デフォルト（docker-compose 内）
        }

        this.webClient = WebClient.builder()
            .baseUrl("http://" + cppHost)
            .build();
    }


    public String getCharacterType(SettingsInDTO settingsInDTO) {
        String charactertype = webClient.post()
                .uri("/characterType")
                .bodyValue(settingsInDTO)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        if (charactertype == null) {
            throw new RuntimeException("Character type not found");
        }

        return charactertype;
    }
}