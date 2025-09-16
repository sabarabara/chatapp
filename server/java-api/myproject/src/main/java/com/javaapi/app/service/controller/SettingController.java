package com.javaapi.app.service.controller;

import java.util.Enumeration;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsIn_textDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.usecase.Setting.SettingUsecase;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/setting")
public class SettingController {

  private final SettingUsecase settingUsecase;

  public SettingController(SettingUsecase settingUsecase) {
    this.settingUsecase = settingUsecase;
  }

  
  @PostMapping("/create")
  public String createSetting(@RequestBody SettingsIn_textDTO settingsIn_textDTO,HttpServletRequest request) {
    System.out.println("🐞SettingController.createSetting() called");
    String session = request.getHeader("cookie");
    return settingUsecase.createSetting(settingsIn_textDTO,session);
  }

  @GetMapping("/read")
  public SettingsOutDTO readSetting(HttpServletRequest request) {

    Enumeration<String> headerNames = request.getHeaderNames();
    while (headerNames.hasMoreElements()) {
    String name = headerNames.nextElement();
    System.out.println("HEADER: " + name + " = " + request.getHeader(name));
  }

    String session = request.getHeader("cookie");
    System.out.println("🐞SettingController.readSetting() called session:" + session);
    return settingUsecase.readSetting(session);
  }

  @PutMapping("/update")
  public String updateSetting(@RequestBody SettingsInDTO settingsInDTO,String session) {
    return settingUsecase.updateSetting(settingsInDTO,session);
  }

  @DeleteMapping("/delete")
  public String deleteSetting(String session) {
    return settingUsecase.deleteSetting(session);
  }
}