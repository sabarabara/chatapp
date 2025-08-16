package com.javaapi.app.service.core.domain.service.interacter.IDBService.command;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaapi.app.service.core.entity.RoomMemberEntity;

public interface IRoomMemberRepo extends JpaRepository<RoomMemberEntity, String> {
    boolean existsByRoom_IdAndUser_Userid(UUID roomId, UUID userId);
}