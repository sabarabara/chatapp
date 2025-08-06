package com.javaapi.app.service.core.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
@EntityListeners(AuditingEntityListener.class) // ← これを追加！
public class UserProfileEntity {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "blood_type")
    private String bloodType;

    private Integer height;

    private LocalDate birthday;

    @Column(name = "favorite_weather")
    private String favoriteWeather;

    @Column(name = "favorite_color")
    private String favoriteColor;

    @Column(name = "dominant_hand")
    private String dominantHand;

    @Column(name = "character_type")
    private String characterType;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UserProfileEntity() {}

    // コンストラクタ省略可

    public Integer getUserId() {
        return userId;
    }

    public String getBloodType() {
        return bloodType;
    }

    public Integer getHeight() {
        return height;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getFavoriteWeather() {
        return favoriteWeather;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }

    public String getDominantHand() {
        return dominantHand;
    }

    public String getCharacterType() {
        return characterType;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
