package com.programandoenjava.bookingservice.booking.domain.port.out;


public interface PaymentServicePort {
    // El dominio solo quiere saber si el pago tuvo éxito
    boolean processPayment(String userEmail, Long amount);
}