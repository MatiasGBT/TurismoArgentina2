package com.mgbt.turismoargentina_backend.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeHttpRequests()
                .requestMatchers("/api/provinces/admin", "/api/provinces/admin/**").hasRole(ADMIN)
                .requestMatchers("/api/locations/admin", "/api/locations/admin/**").hasRole(ADMIN)
                .requestMatchers("/api/activities/admin", "/api/activities/admin/**").hasRole(ADMIN)
                .requestMatchers("/api/users/admin", "/api/activities/admin/**").hasRole(ADMIN)
                .requestMatchers("/api/purchases/admin", "/api/activities/admin/**").hasRole(ADMIN)
                .anyRequest().permitAll();
        http.oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(jwtAuthConverter);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }
}
