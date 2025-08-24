package com.javaapi.app.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.PackDTO.RandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.service.usecase.Pack.PackUsecase;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/pack")
public class PackController{

  private final PackUsecase packUsecase;
  public PackController(PackUsecase packUsecase) {
    this.packUsecase = packUsecase;
  }

  @GetMapping("/randampack")
  public List<RandamPackDTO> getRandamPack(HttpSession session) {
    return packUsecase.getRandamPack(session);
  }

  @GetMapping("/recommnedampack")
  public List<RecommnedPackDTO> getRecommendPack(HttpSession session) {
    return packUsecase.getRecommendPack(session);
  }
}