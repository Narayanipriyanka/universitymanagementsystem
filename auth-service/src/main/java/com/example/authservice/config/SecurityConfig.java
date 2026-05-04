package com.example.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html", "/auth/login","/auth/register"
                        ).permitAll()
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/configuration/ui",
                                "/configuration/security"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .anyRequest().authenticated()
                );
        return http.build();
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }

}
