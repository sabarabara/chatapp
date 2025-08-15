package com.javaapi.app.service.core.domain.service.interacter.IDBService.query;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapi.app.service.core.entity.UserProfileEntity;

public interface ISettingRepo extends JpaRepository<UserProfileEntity, String> {

    UserProfileEntity findByUserId(UUID userId);
}