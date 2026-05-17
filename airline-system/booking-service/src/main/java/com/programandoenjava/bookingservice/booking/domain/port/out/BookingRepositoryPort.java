package com.programandoenjava.bookingservice.booking.domain.port.out;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepositoryPort {
    Booking save(Booking booking);

    Optional<Booking> findById(UUID id);
}