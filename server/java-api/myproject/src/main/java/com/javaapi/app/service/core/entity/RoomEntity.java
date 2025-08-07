package com.javaapi.app.service.core.entity;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String type;
    private String title;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private Boolean deleted;
    private Integer views;
    private Boolean pinned;

    @OneToMany(mappedBy = "room")
    private List<RoomMemberEntity> members;

    @OneToMany(mappedBy = "room")
    private List<MessageEntity> messages;

    @OneToMany(mappedBy = "room")
    private List<ReadStatusEntity> readStatuses;

    @OneToMany(mappedBy = "room")
    private List<PostLikeEntity> postLikes;

    @OneToMany(mappedBy = "room")
    private List<RoomTagEntity> roomTags;


    public RoomEntity() {}

    public RoomEntity(String type, String title) {
        this.type = type;
        this.title = title;
        this.deleted = false;
        this.views = 0;
        this.pinned = false;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public Integer getViews() {
        return views;
    }

    public Boolean getPinned() {
        return pinned;
    }

    public List<RoomMemberEntity> getMembers() {
        return members;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public List<ReadStatusEntity> getReadStatuses() {
        return readStatuses;
    }

    public List<PostLikeEntity> getPostLikes() {
        return postLikes;
    }

    public List<RoomTagEntity> getRoomTags() {
        return roomTags;
    }


}