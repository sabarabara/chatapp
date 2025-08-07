package com.javaapi.app.service.core.entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

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

    public RoomTagEntity() {}
    public RoomTagEntity(RoomEntity room, TagEntity tag) {
        this.room = room;
        this.tag = tag;
        this.id = new RoomTagId(room.getId(), tag.getId());
    }

    public RoomTagId getId() {
        return id;
    }
    public RoomEntity getRoom() {
        return room;
    }
    public TagEntity getTag() {
        return tag;
    }
}
