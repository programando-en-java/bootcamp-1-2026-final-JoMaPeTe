package com.programandoenjava.bookingservice.booking.application.dto;

public record BookingResponseDto(String id, String flightNumber, Long passengerId, String status) {
}
