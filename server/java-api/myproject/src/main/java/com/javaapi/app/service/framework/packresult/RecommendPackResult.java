package com.javaapi.app.service.framework.packresult;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.user.core.domain.model.vo.Userid;

@Service
public class RecommendPackResult {

    private final WebClient webClient;

    public RecommendPackResult() {
        this.webClient = WebClient.builder()
            .baseUrl("http://localhost:8080/pack")
            .build();
    }

    public List<RecommnedPackDTO> getRecommendedPack(Userid userid) {

        Optional<List<RecommnedPackDTO>> resRecommendPack = webClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/recommnedampack")
                .queryParam("userid", userid.getUserid())
                .build()
            )
            .retrieve()
            .bodyToFlux(RecommnedPackDTO.class)
            .collectList()
            .blockOptional();

        if (resRecommendPack.isEmpty()) {
            throw new IllegalStateException(
                "No recommended packs found for userid: " + userid.getUserid()
            );
        }

        return resRecommendPack.get();
    }
}
