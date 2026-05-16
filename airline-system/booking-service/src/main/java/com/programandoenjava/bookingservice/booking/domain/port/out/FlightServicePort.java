package com.programandoenjava.bookingservice.booking.domain.port.out;

public interface FlightServicePort {
    boolean hasAvailableSeats(String flightNumber, int requestedSeats);
    void reserveSeats(String flightNumber, int seats);
}