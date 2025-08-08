package com.javaapi.app.service.framework.packresult;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;



@Service
public class RecommendPackResult {

  private final WebClient webClient = WebClient.create();
  String baseUrl = "http://localhost:8080/pack";

    public List<RecommnedPackDTO> getRecommendedPack() {

      //データフェッチ
      Optional<List<RecommnedPackDTO>> resRecommendPack = webClient.get()
          .uri(baseUrl + "/recommnedampack")
          .retrieve()
          .bodyToFlux(RecommnedPackDTO.class)
          .collectList()
          .blockOptional();

          if(resRecommendPack.isPresent()) {
              //error handling
          }

          List<RecommnedPackDTO> RecommendPack = resRecommendPack.get();
        return RecommendPack;
    }
}