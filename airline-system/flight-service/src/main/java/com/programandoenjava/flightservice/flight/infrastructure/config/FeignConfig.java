package com.programandoenjava.flightservice.flight.infrastructure.config;

import feign.Logger;
import feign.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public Request.Options requestOptions() {
        return new Request.Options(
                3000,   // connectTimeout: 3 segundos
                8000    // readTimeout: 8 segundos
        );
    }

    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.FULL;  // Logging completo de Feign para debug
    }
}