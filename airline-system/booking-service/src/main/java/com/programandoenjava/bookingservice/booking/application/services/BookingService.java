package com.programandoenjava.bookingservice.booking.application.services;

import com.programandoenjava.bookingservice.booking.application.dto.BookingRequestDto;
import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import com.programandoenjava.bookingservice.booking.domain.exceptions.OverbookingException;
import com.programandoenjava.bookingservice.booking.domain.port.in.CreateBookingUseCase;
import com.programandoenjava.bookingservice.booking.domain.port.out.BookingRepositoryPort;
import com.programandoenjava.bookingservice.booking.domain.port.out.FlightServicePort;
import java.util.UUID;
import jakarta.transaction.Transactional;
public class BookingService implements CreateBookingUseCase {
    private final BookingRepositoryPort bookingRepository;
    private final FlightServicePort flightServicePort; // Puerto para hablar con Flight-Service

    public BookingService(BookingRepositoryPort bookingRepository, FlightServicePort flightServicePort) {

        this.bookingRepository = bookingRepository;
        this.flightServicePort = flightServicePort;
    }


    @Transactional
    public Booking createBooking(BookingRequestDto request) {
        // Lógica US-004: Validar disponibilidad a través del puerto
        if (!flightServicePort.hasAvailableSeats(request.flightNumber(), request.seats())) {
            throw new OverbookingException();
        }

        // Crear la reserva en estado PENDING
        Booking booking = new Booking(UUID.randomUUID(), request.flightNumber(), request.passengerId(), "PENDING");

        // Confirmar reserva de asientos en el otro microservicio
        flightServicePort.reserveSeats(request.flightNumber(), request.seats());

        return bookingRepository.save(booking);
    }
}