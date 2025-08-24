package com.javaapi.app.service.core.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;


@Embeddable
public class RoomTagId implements Serializable {
    private UUID roomId;
    private UUID tagId;

    public RoomTagId() {}
    public RoomTagId(UUID roomId, UUID tagId) {
        this.roomId = roomId;
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomTagId)) return false;
        RoomTagId that = (RoomTagId) o;
        return roomId.equals(that.roomId) && tagId.equals(that.tagId)
                && roomId != null && tagId != null;

    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, tagId);
    }
}