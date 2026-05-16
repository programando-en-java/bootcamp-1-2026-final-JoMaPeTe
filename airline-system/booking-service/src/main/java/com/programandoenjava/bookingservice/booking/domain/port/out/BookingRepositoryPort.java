package com.programandoenjava.bookingservice.booking.domain.port.out;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;

public interface BookingRepositoryPort {
    Booking save(Booking booking);
}