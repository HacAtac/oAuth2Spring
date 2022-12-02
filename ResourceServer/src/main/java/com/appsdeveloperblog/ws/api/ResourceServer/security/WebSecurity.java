//package com.appsdeveloperblog.ws.api.ResourceServer.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//public class WebSecurity  {
//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//        //Registered (Authentication convertor class) with HttpSecurity
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
//
//        http
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET, "/api/v1/users/status/check")
//                .hasAuthority("role")
//                .anyRequest().authenticated()
//                .and()
//                .oauth2ResourceServer()
//                .jwt()
//                .jwtAuthenticationConverter(jwtAuthenticationConverter);
//        return http.build();
//    }
//}

package com.appsdeveloperblog.ws.api.ResourceServer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) //This annotation is used to enable @Secured annotation, @PreAuthorize and @PostAuthorize
@EnableWebSecurity
public class WebSecurity  {
    @Bean //bean means that this method will return an object that will be managed by Spring and will be available for injection into other classes
    protected void configure(HttpSecurity http) throws Exception {

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());

        http
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/users/status/check")
                //.hasAuthority("SCOPE_profile")
                .hasRole("developer")
                //.hasAnyRole("developer","user")
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter);
    }

}


