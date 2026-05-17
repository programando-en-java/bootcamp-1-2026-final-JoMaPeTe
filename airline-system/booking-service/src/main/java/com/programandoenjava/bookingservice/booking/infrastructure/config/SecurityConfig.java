package com.programandoenjava.bookingservice.booking.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Cambiamos permitAll por authenticated
                )
                .httpBasic(Customizer.withDefaults()); // Activamos la autenticación Basic

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Aquí definimos la credencial fija que mencionas
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}bootcamp2026") // {noop} indica que no hay encriptación para pruebas
                .roles("ADMIN") // El rol ADMIN que pide el MVP
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}