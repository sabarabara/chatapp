package com.javaapi.app.service.core.domain.service.interacter.IDBService.query;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaapi.app.service.core.dto.ConversationDTO.IConversationDTO;
import com.javaapi.app.service.core.dto.ConversationDTO.IConversationMemoryDTO;
import com.javaapi.app.service.core.entity.RoomMemberEntity;

public interface IConversationRepo extends JpaRepository<RoomMemberEntity, String> {

    @Query("""
    SELECT rm.id AS roomId,
       u.username AS username
    FROM RoomMemberEntity r1
    JOIN r1.room rm
    JOIN rm.members r2
    JOIN r2.user u
    WHERE r1.user.id = :userId
        AND r2.user.id <> :userId
        AND rm.id IN (
      SELECT rm2.room.id
      FROM RoomMemberEntity rm2
      GROUP BY rm2.room.id
      HAVING COUNT(rm2.user.id) = 2
    )
    """)
    List<IConversationDTO> findConnectedUsersInTwoMemberRooms(@Param("userId") String userId, Pageable pageable);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query("""
    SELECT
        m.sender.id AS userId,
        m.sender.username AS username,
        m.room.id AS roomId,
        m.id AS messageId,
        m.content AS messageContent,
        m.createdAt AS createdAt
    FROM MessageEntity m
    WHERE m.room.id = :roomId
    ORDER BY m.createdAt ASC
    """)
    List<IConversationMemoryDTO> findMessagesByRoomId(@Param("roomId") UUID roomId);

}
