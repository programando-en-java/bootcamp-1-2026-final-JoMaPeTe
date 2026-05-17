package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import com.programandoenjava.bookingservice.booking.domain.port.out.FlightServicePort;
import org.springframework.stereotype.Component;


public class FlightServiceAdapter implements FlightServicePort {

    private final FlightClient flightClient;

    public FlightServiceAdapter(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    @Override
    public boolean hasAvailableSeats(FlightNumber flightNumber, int requestedSeats) {
        FlightExternalDto flight = flightClient.getFlightByNumber(flightNumber.value());
        return flight.availableSeats() >= requestedSeats;
    }

    @Override
    public void reserveSeats(FlightNumber flightNumber, int seats) {
        flightClient.updateFlightSeats(flightNumber.value(), seats);
    }
}