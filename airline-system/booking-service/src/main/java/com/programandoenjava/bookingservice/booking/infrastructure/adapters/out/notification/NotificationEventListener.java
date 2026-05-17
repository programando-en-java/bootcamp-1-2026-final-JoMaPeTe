package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.notification;

import com.programandoenjava.bookingservice.booking.application.events.BookingCreatedEvent;
import com.programandoenjava.bookingservice.booking.application.events.PaymentProcessedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationEventListener {

    // 🎧 Spring detecta esta anotación y ejecuta el método cuando oye el evento
    @EventListener
    public void onBookingCreated(BookingCreatedEvent event) {
        System.out.println("========================================================");
        System.out.println("🔔 EVENTO ESCUCHADO: BookingCreatedEvent");
        System.out.println("📧 Enviando email a: " + event.passengerEmail());
        System.out.println("MENSAJE: Tu reserva " + event.bookingId() + " está pendiente de pago.");
        System.out.println("========================================================\n");
    }

    @EventListener
    public void onPaymentProcessed(PaymentProcessedEvent event) {
        if (event.isSuccess()) {
            System.out.println("========================================================");
            System.out.println("🔔 EVENTO ESCUCHADO: PaymentProcessedEvent (ÉXITO)");
            System.out.println("📧 Enviando ticket a: " + event.passengerEmail());
            System.out.println("🎫 Reserva ID: " + event.bookingId());
            System.out.println("💰 Importe pagado: " + event.amount() + " €");
            System.out.println("✈️ ¡Prepara las maletas, nos vamos!");
            System.out.println("========================================================\n");
        } else {
            System.out.println("🔔 EVENTO: Pago fallido para la reserva " + event.bookingId() + ". Avisando al cliente...");
        }
    }
}
