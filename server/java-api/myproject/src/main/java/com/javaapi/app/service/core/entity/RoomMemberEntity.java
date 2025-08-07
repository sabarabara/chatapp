package com.javaapi.app.service.core.entity;
import com.javaapi.app.user.core.entity.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// --- RoomMemberEntity ---
@Entity
@Table(name = "room_members")
public class RoomMemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public RoomMemberEntity() {}
    public RoomMemberEntity(RoomEntity room, UserEntity user) {
        this.room = room;
        this.user = user;
    }
    public String getId() {
        return id;
    }
    public RoomEntity getRoom() {
        return room;
    }
    public UserEntity getUser() {
        return user;
    }
}
