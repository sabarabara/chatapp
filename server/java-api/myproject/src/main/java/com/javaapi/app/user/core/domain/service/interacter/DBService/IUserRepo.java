package com.javaapi.app.user.core.domain.service.interacter.DBService;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapi.app.user.core.entity.UserEntity;

public interface IUserRepo extends JpaRepository<UserEntity, UUID>{

    UserEntity findByUserid(UUID userid);
}