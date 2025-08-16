package com.javaapi.app.service.framework.packresult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.PackDTO.RecievedPackDTO;
import com.javaapi.app.user.core.domain.model.vo.Userid;

@Service
public class RecommendPackResult {

    private final WebClient webClient;

    public RecommendPackResult() {
        this.webClient = WebClient.builder()
            .baseUrl("http://172.31.192.48:6000")
            .build();
    }

    public List<RecievedPackDTO> getRecommendedPack(Userid userid) {
        Optional<List<RecievedPackDTO>> resRecommendPack = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/similar_users_advice")
                .queryParam("user_id", userid.getUserid())
                .queryParam("top_k", 1)
                .build()
            )
            .retrieve()
            .bodyToFlux(RecievedPackDTO.class)
            .collectList()
            .blockOptional();

        if (resRecommendPack.isEmpty()) {
            throw new IllegalStateException(
                "No recommended packs found for userid: " + userid.getUserid()
            );
        }

        return resRecommendPack.get();
    }


    public String registerHobby(Userid userid, String hobbyText) {
        Map<String, Object> requestBody = Map.of(
            "user_id", userid.getUserid(),
            "hobby_text", hobbyText
        );

        webClient.post()
            .uri("/register_hobby")
            .bodyValue(requestBody)
            .retrieve()
            .bodyToMono(Void.class)
            .block();
        return "OK_registerHobby";
    }
}
