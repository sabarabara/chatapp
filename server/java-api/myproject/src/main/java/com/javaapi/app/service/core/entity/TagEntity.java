package com.javaapi.app.service.core.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// --- TagEntity ---
@Entity
@Table(name = "tags")
public class TagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "tag")
    private List<RoomTagEntity> roomTags;

    public TagEntity() {}
    public TagEntity(String name) {
        this.name = name;
    }
    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<RoomTagEntity> getRoomTags() {
        return roomTags;
    }
}