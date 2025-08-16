package com.javaapi.app.service.controller;

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

import jakarta.servlet.http.HttpSession;


@RestController
@RequestMapping("/setting")
public class SettingController {

  private final SettingUsecase settingUsecase;

  public SettingController(SettingUsecase settingUsecase) {
    this.settingUsecase = settingUsecase;
  }

  
  @PostMapping("/create")
  public String createSetting(@RequestBody SettingsIn_textDTO settingsIn_textDTO,HttpSession session) {
    System.out.println("🐞SettingController.createSetting() called");
    return settingUsecase.createSetting(settingsIn_textDTO,session);
  }

  @GetMapping("/read")
  public SettingsOutDTO readSetting(HttpSession session) {
    return settingUsecase.readSetting(session);
  }

  @PutMapping("/update")//とりあえずstring
  public String updateSetting(@RequestBody SettingsInDTO settingsInDTO,HttpSession session) {
    return settingUsecase.updateSetting(settingsInDTO,session);
  }

  @DeleteMapping("/delete")
  public String deleteSetting(HttpSession session) {
    return settingUsecase.deleteSetting(session);
  }
}