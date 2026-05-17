package com.programandoenjava.bookingservice.booking.infrastructure.adapters.in.web;

import com.programandoenjava.bookingservice.booking.application.dto.BookingRequestDto;
import com.programandoenjava.bookingservice.booking.application.dto.BookingResponseDto;
import com.programandoenjava.bookingservice.booking.application.services.BookingService;
import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto request) {
        // Ejecutamos la lógica de negocio a través del servicio
        Booking booking = bookingService.createBooking(request);
        
        // Mapeamos a un DTO de respuesta
        BookingResponseDto response = new BookingResponseDto(
                booking.getId().value().toString(),      // Extraemos el UUID y lo hacemos String
                booking.getFlightNumber().value(),       // Extraemos el String del vuelo
                booking.getPassengerId().value(),         // Extraemos el Long del pasajero
                booking.getStatus().name()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

