package com.javaapi.app.service.usecase.Home;

import org.springframework.stereotype.Service;

import com.javaapi.app.service.core.dto.HomeDTO.HomeDTO;

@Service
public class HomeUsecase {

    public HomeUsecase() {}

    public HomeDTO getHomeData(String session) {
        return new HomeDTO("TestUser", "Knight", 100);
    }
}