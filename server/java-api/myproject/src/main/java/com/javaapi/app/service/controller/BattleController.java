package com.javaapi.app.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;

@RestController
RequestMapping("/battle")
public class BattleController {

    private final BattleUsecase BattleUsecase;

    public BattleController(BattleUsecase BattleUsecase) {
        this.BattleUsecase = BattleUsecase;
    }

    @GetMapping()
    public BattleDTO getbattleUser(){
        return BattleUsecase.getBattleUserDetails();
    }
}