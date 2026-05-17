package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto.PaymentRequestDto;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto.PaymentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "payment-service", url = "http://localhost:8083")
public interface PaymentClient {
    
    @PostMapping("/api/v1/payments")
    PaymentResponseDto process(@RequestBody PaymentRequestDto request);
}