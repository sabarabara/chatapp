package com.javaapi.app.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.javaapi.app.service.core.dto.SettingsDTO.SettingsInDTO;
import com.javaapi.app.service.core.dto.SettingsDTO.SettingsOutDTO;


@RestController
@RequestMapping("/setting")
public class SettingController {

  @PostMapping("/create")
  public String createSetting(@RequestBody SettingsInDTO settingsInDTO) {
    return "Setting created successfully";
  }

  @GetMapping("/read")
  public SettingsOutDTO readSetting() {
    return "Setting read successfully";
  }

  @PutMapping("/update")
  public SettingsInDTO updateSetting(@RequestBody SettingsInDTO settingsInDTO) {
    return "Setting updated successfully";
  }

  @DeleteMapping("/delete")
  public String deleteSetting() {
    return "Setting deleted successfully";
  }
}