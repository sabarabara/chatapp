package com.javaapi.app.service.core.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javaapi.app.user.core.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// --- MessageEntity ---
@Entity
@Table(name = "messages")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private UserEntity sender;

    @Column(columnDefinition = "TEXT")
    private String content;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private Boolean deleted;

    @Column(name = "parent_message_id")
    private Integer parentMessageId;

    @OneToMany(mappedBy = "message")
    private List<CommentLikeEntity> commentLikes;

    @OneToMany(mappedBy = "message")
    private List<AttachmentEntity> attachments;


    public MessageEntity() {}

    public MessageEntity(RoomEntity room, UserEntity sender, String content) {
        this.room = room;
        this.sender = sender;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public UserEntity getSender() {
        return sender;
    }
    public String getContent() {
        return content;
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

    public Integer getParentMessageId() {
        return parentMessageId;
    }

    public List<CommentLikeEntity> getCommentLikes() {
        return commentLikes;
    }

    public List<AttachmentEntity> getAttachments() {
        return attachments;
    }
}