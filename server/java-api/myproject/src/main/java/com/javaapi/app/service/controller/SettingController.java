package com.javaapi.app.service.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;
import com.javaapi.app.service.usecase.Setting.SettingUsecase;


@RestController
@RequestMapping("/setting")
public class SettingController {

  private final SettingUsecase settingUsecase;

  public SettingController(SettingUsecase settingUsecase) {
    this.settingUsecase = settingUsecase;
  }



  @PostMapping("/create")
  public String createSetting(@RequestBody SettingsInDTO settingsInDTO) {
    return settingUsecase.createSetting(settingsInDTO);
  }

  @GetMapping("/read")
  public SettingsOutDTO readSetting() {
    return settingUsecase.readSetting();
  }

  @PutMapping("/update")
  public SettingsOutDTO updateSetting(@RequestBody SettingsInDTO settingsInDTO) {
    return settingUsecase.updateSetting(settingsInDTO);
  }

  @DeleteMapping("/delete")
  public String deleteSetting() {
    return settingUsecase.deleteSetting();
  }
}