package com.javaapi.app.service.core.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;


@Embeddable
public class RoomTagId implements Serializable {
    private String roomId;
    private String tagId;

    public RoomTagId() {}
    public RoomTagId(String roomId, String tagId) {
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