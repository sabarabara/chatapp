package com.javaapi.app.service.framework.packresult;

import com.javaapi.app.service.core.dto.PackDTO.PackDTO;
import java.util.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


@Service
public class PackResult {

  private final WebClient webClient = WebClient.create();
  String baseUrl = "http://localhost:8080/pack";

    public List<PackDTO> getRandomPack() {
      //データフェッチ
      Optional<List<PackDTO>> resRandomPack = webClient.get()
          .uri(baseUrl + "/randampack")
          .retrieve()
          .bodyToFlux(PackDTO.class)
          .collectList()
          .blockOptional();

          if(resRandomPack.isPresent()) {
              //error handling
          }

          List<PackDTO> RandamPack = resRandomPack.get();
        return RandamPack;
    }

    public List<PackDTO> getRecommendedPack() {

      //データフェッチ
      Optional<List<PackDTO>> resRecommendPack = webClient.get()
          .uri(baseUrl + "/recommnedampack")
          .retrieve()
          .bodyToFlux(PackDTO.class)
          .collectList()
          .blockOptional();

          if(resRecommendPack.isPresent()) {
              //error handling
          }

          List<PackDTO> RecommendPack = resRecommendPack.get();
        return RecommendPack;
    }
}