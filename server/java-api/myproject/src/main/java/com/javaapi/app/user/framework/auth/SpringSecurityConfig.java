package com.javaapi.app.user.framework.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

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
                .requestMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2Login(oauth2 -> oauth2
                .successHandler(oidcSuccessHandler)
            );

        return http.build();
    }
}