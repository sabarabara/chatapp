package com.javaapi.app.service.core.entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// --- RoomTagEntity ---
@Entity
@Table(name = "room_tags")
public class RoomTagEntity {
    @EmbeddedId
    private RoomTagId id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private TagEntity tag;

    
}
