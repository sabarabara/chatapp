package com.javaapi.app.user.core.domain.service.interacter.DBService;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapi.app.user.core.entity.UserEntity;

public interface IUserRepo extends JpaRepository<UserEntity, String>{

    UserEntity findByUserid(String userid);
}