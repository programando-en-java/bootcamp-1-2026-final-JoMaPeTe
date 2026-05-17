package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import com.programandoenjava.bookingservice.booking.domain.port.out.PaymentServicePort;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.PaymentClient;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto.PaymentRequestDto;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign.dto.PaymentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceAdapter implements PaymentServicePort {

    private final PaymentClient paymentClient;

    public PaymentServiceAdapter(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Override
    public boolean processPayment(String userEmail, Long amount) {
        // 1. Traducimos los datos del dominio al DTO de Feign
        PaymentRequestDto request = new PaymentRequestDto(userEmail, amount);
        
        // 2. Hacemos la llamada real al microservicio
        PaymentResponseDto response = paymentClient.process(request);
        
        // 3. Devolvemos solo lo que le importa al dominio (el booleano)
        return response.paymentSucceeded();
    }
}