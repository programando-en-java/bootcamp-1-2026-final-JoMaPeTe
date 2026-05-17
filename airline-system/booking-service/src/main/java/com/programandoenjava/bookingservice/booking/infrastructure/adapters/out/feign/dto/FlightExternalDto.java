package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto;

// DTO interno para mapear la respuesta del flight-service
public record FlightExternalDto(String flightNumber, int availableSeats, long price) {}
