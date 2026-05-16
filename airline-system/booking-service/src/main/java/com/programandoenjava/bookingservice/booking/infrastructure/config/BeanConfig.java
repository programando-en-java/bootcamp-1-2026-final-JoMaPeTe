package com.programandoenjava.bookingservice.booking.infrastructure.config;

import com.programandoenjava.bookingservice.booking.application.services.BookingService;
import com.programandoenjava.bookingservice.booking.domain.port.out.BookingRepositoryPort;

@Configuration
public class BeanConfig {
    @Bean
    public BookingService bookingService(BookingRepositoryPort repo, FlightServicePort flightPort) {
        return new BookingService(repo, flightPort);
    }
}