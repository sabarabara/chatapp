package com.javaapi.app.service.core.entity;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;


@Entity
@Table(name = "rooms")
@EntityListeners(AuditingEntityListener.class)
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    // getter/setter
}