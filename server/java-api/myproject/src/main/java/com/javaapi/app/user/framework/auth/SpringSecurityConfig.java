package com.javaapi.app.user.framework.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())          // CSRF 無効化
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()          // 全リクエストを認証不要に
            )
            .httpBasic(basic -> basic.disable())    // デフォルトのHTTP Basicも無効化
            .formLogin(login -> login.disable());   // デフォルトフォームログインも無効化

        return http.build();
    }
}
