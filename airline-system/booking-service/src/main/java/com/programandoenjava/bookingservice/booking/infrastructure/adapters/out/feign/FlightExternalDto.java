package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

// DTO interno para mapear la respuesta del flight-service
public record FlightExternalDto(String flightNumber, int availableSeats) {}
