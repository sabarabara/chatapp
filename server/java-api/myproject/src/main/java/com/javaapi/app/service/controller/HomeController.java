package com.javaapi.app.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.HomeDTO.HomeDTO;
import com.javaapi.app.service.usecase.Home.HomeUsecase;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/home")
public class HomeController {

    private final HomeUsecase homeUsecase;

    public HomeController(HomeUsecase homeUsecase) {
        this.homeUsecase = homeUsecase;
    }
    @GetMapping()
    public HomeDTO getHomeData(HttpServletRequest request) {
        String session = request.getHeader("cookie");
        return homeUsecase.getHomeData(session);
    }
}