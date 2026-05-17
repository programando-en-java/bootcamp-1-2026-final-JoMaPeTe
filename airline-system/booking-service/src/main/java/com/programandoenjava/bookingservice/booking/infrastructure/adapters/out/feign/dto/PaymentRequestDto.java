package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto;

public record PaymentRequestDto(
    String userEmail,
    Long amount
) {}