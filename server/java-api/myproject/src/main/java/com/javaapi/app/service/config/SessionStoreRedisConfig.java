package com.javaapi.app.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SessionStoreRedisConfig {

    // SessionStore 用 RedisTemplate（JSON文字列保存用）
    @Bean
    public RedisTemplate<String, String> sessionStoreRedisTemplate() {
        // コンテナ名: redis-session, ポート: 6379
        LettuceConnectionFactory factory = new LettuceConnectionFactory("redis-session", 6379);
        factory.afterPropertiesSet();

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);

        // key/value は文字列で扱う
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}
