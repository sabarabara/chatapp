package com.javaapi.app.service.core.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReadStatusId implements Serializable {
    private UUID roomId;
    private UUID userId;

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReadStatusId)) return false;
        ReadStatusId that = (ReadStatusId) o;
        return Objects.equals(roomId, that.roomId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId);
    }
}