package com.javaapi.app.service.core.entity;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.javaapi.app.user.core.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;   


@Entity
@Table(name = "comment_likes")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private MessageEntity message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public CommentLikeEntity() {}

    public CommentLikeEntity(MessageEntity message, UserEntity user) {
        this.message = message;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public MessageEntity getMessage() {
        return message;
    }

    public UserEntity getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}