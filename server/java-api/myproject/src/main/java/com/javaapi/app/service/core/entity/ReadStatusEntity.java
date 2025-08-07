package com.javaapi.app.service.core.entity;
import java.time.LocalDateTime;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.EmbeddedId;

import org.springframework.data.annotation.LastModifiedDate;

import com.javaapi.app.user.core.entity.UserEntity;

@Entity
@Table(name = "read_status")
@EntityListeners(AuditingEntityListener.class)
public class ReadStatusEntity {
    @EmbeddedId
    private ReadStatusId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "last_read_message_id")
    private Integer lastReadMessageId;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public ReadStatusEntity() {}

    public ReadStatusEntity(RoomEntity room, UserEntity user, Integer lastReadMessageId) {
        this.room = room;
        this.user = user;
        this.lastReadMessageId = lastReadMessageId;
    }

    public ReadStatusId getId() {
        return id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public UserEntity getUser() {
        return user;
    }

    public Integer getLastReadMessageId() {
        return lastReadMessageId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}