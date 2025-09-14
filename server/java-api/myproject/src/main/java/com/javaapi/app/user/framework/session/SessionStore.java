package com.javaapi.app.user.framework.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Component
public class SessionStore {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final long sessionTTL = 30 * 60; // 30分

    public SessionStore(@Qualifier("sessionStoreRedisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String key(String session) {
        return "user:session:" + session;
    }

    // ------------------- GET -------------------
    public String getUserid(String session) {
        UserInfo userInfo = getUserInfo(session);
        return userInfo != null ? userInfo.getUserId() : null;
    }

    public String getUsername(String session) {
        UserInfo userInfo = getUserInfo(session);
        return userInfo != null ? userInfo.getUsername() : null;
    }

    public String getEmail(String session) {
        UserInfo userInfo = getUserInfo(session);
        return userInfo != null ? userInfo.getEmail() : null;
    }

    private UserInfo getUserInfo(String session) {
        Object obj = redisTemplate.opsForValue().get(key(session));
        if (obj == null) return null;
        return objectMapper.convertValue(obj, UserInfo.class);
    }

    // ------------------- SET -------------------
    public void setUserid(String session, String userId) {
        UserInfo userInfo = getOrCreate(session);
        userInfo.setUserId(userId);
        save(session, userInfo);
    }

    public void setUsername(String session, String username) {
        UserInfo userInfo = getOrCreate(session);
        userInfo.setUsername(username);
        save(session, userInfo);
    }

    public void setEmail(String session, String email) {
        UserInfo userInfo = getOrCreate(session);
        userInfo.setEmail(email);
        save(session, userInfo);
    }

    private UserInfo getOrCreate(String session) {
        UserInfo userInfo = getUserInfo(session);
        return userInfo != null ? userInfo : new UserInfo();
    }

    private void save(String session, UserInfo userInfo) {
        redisTemplate.opsForValue().set(key(session), userInfo, sessionTTL, TimeUnit.SECONDS);
    }

    // ------------------- 内部クラス -------------------
    public static class UserInfo {
        private String userId;
        private String username;
        private String email;

        public UserInfo() {}

        public String getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }

        public void setUserId(String userId) { this.userId = userId; }
        public void setUsername(String username) { this.username = username; }
        public void setEmail(String email) { this.email = email; }
    }
}
