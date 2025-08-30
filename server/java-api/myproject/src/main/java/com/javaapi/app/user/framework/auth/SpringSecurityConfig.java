package com.javaapi.app.user.framework.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    private final OIDCSuccessHandler oidcSuccessHandler;

    public SpringSecurityConfig(OIDCSuccessHandler oidcSuccessHandler) {
        this.oidcSuccessHandler = oidcSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login/oauth2/code/cognito", "/oauth2/authorization/cognito").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(oidcSuccessHandler)
            ); // ← oauth2ResourceServer は削除

        return http.build();
    }
}
