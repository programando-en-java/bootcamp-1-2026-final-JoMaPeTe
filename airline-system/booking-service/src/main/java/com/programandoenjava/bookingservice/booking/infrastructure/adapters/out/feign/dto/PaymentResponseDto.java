package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto;

public record PaymentResponseDto(
    String processorName,
    Long amount,
    String userEmail,
    Boolean paymentSucceeded
) {}