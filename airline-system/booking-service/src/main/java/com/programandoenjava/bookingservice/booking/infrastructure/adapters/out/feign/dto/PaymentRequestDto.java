package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto;

public record PaymentRequestDto(
        String bookingId,
    String userEmail,
    Long amount
) {}