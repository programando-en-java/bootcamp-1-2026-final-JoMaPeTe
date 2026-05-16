package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import com.programandoenjava.bookingservice.booking.domain.port.out.FlightServicePort;
import org.springframework.stereotype.Component;

@Component
public class FlightServiceAdapter implements FlightServicePort {

    private final FlightClient flightClient;

    public FlightServiceAdapter(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    @Override
    public boolean hasAvailableSeats(String flightNumber, int requestedSeats) {
        FlightExternalDto flight = flightClient.getFlightByNumber(flightNumber);
        return flight.availableSeats() >= requestedSeats;
    }

    @Override
    public void reserveSeats(String flightNumber, int seats) {
        flightClient.updateFlightSeats(flightNumber, seats);
    }
}