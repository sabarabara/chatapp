package com.javaapi.app.service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;
import com.javaapi.app.service.usecase.Battle.BattleUsecase;


@RestController
@RequestMapping("/battle")
public class BattleController {

    private final BattleUsecase battleUsecase;

    public BattleController(BattleUsecase battleUsecase) {
        this.battleUsecase = battleUsecase;
    }

    @GetMapping()
    public List<BattleDTO> getBattleUser(HttpSession session) {
        return battleUsecase.getBattleUserDetails(session);
    }
}