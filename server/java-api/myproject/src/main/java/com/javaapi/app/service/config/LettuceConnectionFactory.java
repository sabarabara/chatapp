/*package com.javaapi.app.service.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;


public class LettuceConnectionFactory {

        @Value("${spring.data.redis.host}")
        private String hostName;
    
        @Value("${spring.data.redis.port}")
        private int port;
    
        @Bean
        public LettuceConnectionFactory lettuceConnectionFactory() {
       
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(hostName);
            redisStandaloneConfiguration.setPort(port);
            return new LettuceConnectionFactory(redisStandaloneConfiguration);
        }
}
*/