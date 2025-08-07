package com.javaapi.app.service.core.domain.service.interacter.IDBService.command;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapi.app.service.core.entity.RoomEntity;

public interface IRoomRepo extends JpaRepository<RoomEntity, String> {}