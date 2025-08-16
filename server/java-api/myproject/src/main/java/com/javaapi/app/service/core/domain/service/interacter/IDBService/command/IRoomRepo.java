package com.javaapi.app.service.core.domain.service.interacter.IDBService.command;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaapi.app.service.core.entity.RoomEntity;

public interface IRoomRepo extends JpaRepository<RoomEntity, String> {

    @Query("SELECT r FROM RoomEntity r " +
           "JOIN r.members m1 " +
           "JOIN r.members m2 " +
           "WHERE m1.user.userid = :userId1 " +
           "AND m2.user.userid = :userId2")
    Optional<RoomEntity> findCommonRoom(@Param("userId1") UUID userId1,
                                        @Param("userId2") UUID userId2);
}
