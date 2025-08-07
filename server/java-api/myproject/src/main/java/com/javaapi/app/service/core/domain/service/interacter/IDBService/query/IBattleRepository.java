package com.javaapi.app.service.core.domain.service.interacter.IDBService.query;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.javaapi.app.service.core.dto.BattleDTO.BattleDTO;
import com.javaapi.app.service.core.entity.RoomMemberEntity;

public interface IBattleRepository extends JpaRepository<RoomMemberEntity, String> {

    @Query("""
        SELECT u.id AS userId, u.username AS username, p.characterType AS characterType
        FROM RoomEntity r
        JOIN r.members rm1
        JOIN r.members rm2
        JOIN UserEntity u ON rm2.user.id = u.id
        JOIN UserProfileEntity p ON u.id = p.user.id
        WHERE rm1.user.id = :userId
          AND rm2.user.id != :userId
          AND SIZE(r.members) = 2
    """)
    List<BattleDTO> findOneToOneConversationPartners(@Param("userId") String userId);
}
