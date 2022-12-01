package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class WebSecurity  {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/api/v1/users/status/check")
                .hasAuthority("SCOPE_profile")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }
}



