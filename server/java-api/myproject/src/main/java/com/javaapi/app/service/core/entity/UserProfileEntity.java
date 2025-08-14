package com.javaapi.app.service.core.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.javaapi.app.user.core.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profiles")
@EntityListeners(AuditingEntityListener.class)
public class UserProfileEntity {

    @Id
    @Column(name = "user_id")
    private String userId;

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

    // ここを追加
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserProfileEntity() {}

    public UserProfileEntity(String userId, String bloodType, Integer height, LocalDate birthday,
                             String favoriteWeather, String favoriteColor, String dominantHand, String characterType) {
        this.userId = userId;
        this.bloodType = bloodType;
        this.height = height;
        this.birthday = birthday;
        this.favoriteWeather = favoriteWeather;
        this.favoriteColor = favoriteColor;
        this.dominantHand = dominantHand;
        this.characterType = characterType;
    }

    // getter/setter
    public String getUserId() {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
