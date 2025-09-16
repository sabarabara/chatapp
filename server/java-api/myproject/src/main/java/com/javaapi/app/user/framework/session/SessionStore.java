package com.javaapi.app.user.framework.session;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import com.javaapi.app.user.core.dto.SessionDTO;

import jakarta.websocket.Session;

@Component
public class SessionStore {

    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final long sessionTTL = 30 * 60; // 30分

    public SessionStore(@Qualifier("sessionStoreRedisTemplate") RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String key(String session) {
        return "user:session:" + session;
    }

    // ------------------- 一括保存 -------------------
    public void saveSession(String session, String userId, String username, String email) {
        try {
            SessionDTO userInfo = new SessionDTO(userId, username, email);
            String json = objectMapper.writeValueAsString(userInfo);
            redisTemplate.opsForValue().set(key(session), json, sessionTTL, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save session", e);
        }
    }

    // ------------------- GET -------------------
    public SessionDTO getUserInfo(String session) {
        String json = redisTemplate.opsForValue().get(key(session));
        if (json == null) return null;
        try {
            SessionDTO info = objectMapper.readValue(json, SessionDTO.class);
            System.out.println("🐞 Parsed UserInfo: userId=" + info.getUserId());
            return info;
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse session JSON", e);
        }
    }
}