package com.javaapi.app.service.core.domain.service.interacter.IDBService.query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaapi.app.service.core.dto.ForumDTO.IForumDTO;
import com.javaapi.app.service.core.dto.ForumDTO.IForumMemoryDTO;
import com.javaapi.app.service.core.entity.RoomMemberEntity;

public interface IForumRepo extends JpaRepository<RoomMemberEntity, String> {

    @Query("""
    SELECT 
        r2.room.id AS roomId,
        u.id AS userId,
        u.username AS username,
        r2.room.title AS title
    FROM RoomMemberEntity r1
    JOIN r1.room rm
    JOIN rm.members r2
    JOIN r2.user u
    WHERE r1.user.id = :userId
      AND r2.user.id <> :userId
      AND rm.id IN (
          SELECT rm2.id
          FROM RoomMemberEntity rm2
          GROUP BY rm2.room.id
          HAVING COUNT(rm2.user.id) >= 3
      )
    """)
    List<IForumDTO> findConnectedUsersInBulletinRooms(@Param("userId") Integer userId, Pageable pageable);

    ///////////////////////////////////////
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
    List<IForumMemoryDTO> findBulletinMessagesByRoomId(@Param("roomId") Integer roomId);
}
