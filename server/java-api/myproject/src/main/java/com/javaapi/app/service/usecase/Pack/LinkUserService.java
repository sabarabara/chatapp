package com.javaapi.app.service.usecase.Pack;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IRoomMemberRepo;
import com.javaapi.app.service.core.domain.service.interacter.IDBService.command.IRoomRepo;
import com.javaapi.app.service.core.entity.RoomEntity;
import com.javaapi.app.service.core.entity.RoomMemberEntity;
import com.javaapi.app.user.core.domain.service.interacter.DBService.IUserRepo;
import com.javaapi.app.user.core.entity.UserEntity;

@Service
public class LinkUserService {

    private final IUserRepo userRepository;
    private final IRoomRepo roomRepository;
    private final IRoomMemberRepo roomMemberRepository;

    public LinkUserService(IUserRepo userRepository,
                           IRoomRepo roomRepository,
                           IRoomMemberRepo roomMemberRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.roomMemberRepository = roomMemberRepository;
    }

    @Transactional
    public RoomEntity linkUsersInNewRoom(UUID userId1, UUID userId2) {
        UserEntity user1 = userRepository.findByUserid(userId1);
        if (user1 == null) throw new IllegalArgumentException("User1 not found");

        UserEntity user2 = userRepository.findByUserid(userId2);
        if (user2 == null) throw new IllegalArgumentException("User2 not found");

        
        Optional<RoomEntity> existingRoom = roomRepository.findCommonRoom(userId1, userId2);
        if (existingRoom.isPresent()) {
            
            return existingRoom.get();
        }

        // 共通ルームがなければ新規作成
        RoomEntity room = new RoomEntity();
        room.setType("chat");
        room.setTitle("二人用ルーム");
        roomRepository.save(room);

        // ROOM_MEMBERS 登録（重複チェック）
        addUserToRoomIfNotExists(user1, room);
        addUserToRoomIfNotExists(user2, room);

        return room;
    }

    private void addUserToRoomIfNotExists(UserEntity user, RoomEntity room) {
        boolean alreadyMember = roomMemberRepository.existsByRoom_IdAndUser_Userid(room.getId(), user.getUserid());
        if (!alreadyMember) {
            RoomMemberEntity member = new RoomMemberEntity();
            member.setRoom(room);
            member.setUser(user);
            roomMemberRepository.save(member);
        }
    }
}
