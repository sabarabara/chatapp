package com.javaapi.app.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.javaapi.app.service.core.dto.PackDTO.PackDTO;
import com.javaapi.app.service.usecase.PackUsecase;


@RestController
@RequestMapping("/pack")
public class PackController{

  private final PackUsecase packUsecase;
  public PackController(PackUsecase packUsecase) {
    this.packUsecase = packUsecase;
  }

  @GetMapping("/randampack")
  public List<PackDTO> getRandamPack() {
    return packUsecase.getRandamPack();
  }

  @GetMapping("/recommnedampack")
  public List<PackDTO> getRecommendPack() {
    return packUsecase.getRecommendPack();
  }
}