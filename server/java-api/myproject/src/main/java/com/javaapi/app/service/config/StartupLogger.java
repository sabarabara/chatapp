package com.javaapi.app.service.config;

import java.util.Map;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class StartupLogger {
    @Bean
    public ApplicationRunner runner(Environment env) {
        return args -> {
            System.out.println("=== STARTUP ENV & SPRING PROPS ===");
            // すべての環境変数（多いので必要なものだけに絞ってもOK）
            Map<String,String> envs = System.getenv();
            System.out.println("ENV REDIS_HOST=" + envs.get("REDIS_HOST") + " REDIS_PORT=" + envs.get("REDIS_PORT"));
            // Spring environment
            System.out.println("spring.redis.host=" + env.getProperty("spring.redis.host"));
            System.out.println("spring.redis.port=" + env.getProperty("spring.redis.port"));
            System.out.println("spring.redis.database=" + env.getProperty("spring.redis.database"));
            System.out.println("System prop -Dspring.redis.host=" + System.getProperty("spring.redis.host"));
            System.out.println("===================================");
        };
    }
}
