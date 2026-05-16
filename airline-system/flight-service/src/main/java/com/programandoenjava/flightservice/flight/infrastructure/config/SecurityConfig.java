package com.programandoenjava.flightservice.flight.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Desactivamos CSRF porque para APIs REST no suele ser necesario en pruebas
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Permitimos todas las peticiones a cualquier endpoint
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // 3. Opcional: Si vas a usar la consola de H2, esto permite ver los frames
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}