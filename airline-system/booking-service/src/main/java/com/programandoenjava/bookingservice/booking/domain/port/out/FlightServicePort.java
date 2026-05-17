package com.programandoenjava.bookingservice.booking.domain.port.out;

import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;

public interface FlightServicePort {
    boolean hasAvailableSeats(FlightNumber flightNumber, int requestedSeats);
    void reserveSeats(FlightNumber flightNumber, int seats);
    void cancelReserve(FlightNumber flightNumber, Integer quantity);
    long getFlightPrice(FlightNumber flightNumber);
}