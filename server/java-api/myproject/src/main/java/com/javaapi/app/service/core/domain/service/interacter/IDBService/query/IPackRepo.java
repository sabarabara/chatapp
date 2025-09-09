package com.javaapi.app.service.core.domain.service.interacter.IDBService.query;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaapi.app.service.core.dto.PackDTO.IRandamPackDTO;
import com.javaapi.app.service.core.entity.RoomMemberEntity;

public interface IPackRepo extends JpaRepository<RoomMemberEntity, String> {

    @Query("""
        SELECT u.id AS userId, u.username AS username, p.characterType AS characterType
        FROM UserEntity u
        JOIN UserProfileEntity p ON p.user.id = u.id
        WHERE u.id != :userId
          AND u.id NOT IN (
              SELECT rm2.user.id
              FROM RoomEntity r
              JOIN r.members rm1
              JOIN r.members rm2
              WHERE rm1.user.id = :userId
                AND rm2.user.id != :userId
                AND SIZE(r.members) = 2
          )
    """)
    List<IRandamPackDTO> findUnmatchedUsers(@Param("userId") String userId, Pageable pageable);
}
