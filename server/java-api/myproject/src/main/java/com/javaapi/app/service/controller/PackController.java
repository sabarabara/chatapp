package com.javaapi.app.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.PackDTO.PackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RandamPackDTO;
import com.javaapi.app.service.core.dto.PackDTO.RecommnedPackDTO;
import com.javaapi.app.service.usecase.Pack.PackUsecase;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/pack")
public class PackController{

  private final PackUsecase packUsecase;
  public PackController(PackUsecase packUsecase) {
    this.packUsecase = packUsecase;
  }

  @GetMapping("/scene")
  public PackDTO getScene(HttpServletRequest request) {
    String session = request.getHeader("cookie");
    return packUsecase.fetchScene(session);
  }

  @PostMapping("/save")
  public void saveGatyaStone(HttpServletRequest request,@RequestBody PackDTO dto) {
    String session = request.getHeader("cookie");
    packUsecase.saveGatyaStone(session, dto);
  }

  @GetMapping("/randampack")
  public RandamPackDTO getRandamPack(HttpServletRequest request) {
    String session = request.getHeader("cookie");
    return packUsecase.getRandamPack(session);
  }

  @GetMapping("/recommnedampack")
  public RecommnedPackDTO getRecommendPack(HttpServletRequest request) {
    String session = request.getHeader("cookie");
    return packUsecase.getRecommendPack(session);
  }
}