package com.javaapi.app.service.core.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.javaapi.app.user.core.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;





@Entity
@Table(name = "post_likes")
public class PostLikeEntity {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public PostLikeEntity() {}
    public PostLikeEntity(String id, RoomEntity room, UserEntity user) {
        this.id = id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}