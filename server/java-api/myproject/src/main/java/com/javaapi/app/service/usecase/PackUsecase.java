package com.javaapi.app.service.usecase;

import com.javaapi.app.service.core.dto.PackDTO.PackDTO;
import com.javaapi.app.service.framework.packresult.PackResult;

import java.util.List;

public class PackUsecase {

  private final PackResult packResult;

  public PackUsecase(PackResult packResult) {
    this.packResult = packResult;
  }


    public List<PackDTO> getRandamPack() {
        return packResult.getRandomPack();
    }

    public List<PackDTO> getRecommendPack() {
        return packResult.getRecommendedPack();
    }
}