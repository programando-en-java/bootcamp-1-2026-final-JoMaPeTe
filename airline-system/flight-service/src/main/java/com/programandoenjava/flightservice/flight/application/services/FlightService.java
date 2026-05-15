package com.programandoenjava.flightservice.flight.application.services;

import com.programandoenjava.flightservice.flight.domain.entities.Flight;
import com.programandoenjava.flightservice.flight.domain.port.in.ReserveSeatsUseCase;
import com.programandoenjava.flightservice.flight.domain.port.in.SearchFlightsCriteria;
import com.programandoenjava.flightservice.flight.domain.port.in.SearchFlightsUseCase;
import com.programandoenjava.flightservice.flight.domain.port.out.FlightRepositoryPort;
import jakarta.transaction.Transactional;
//import org.springframework.stereotype.Service;

import java.util.List;


public class FlightService implements ReserveSeatsUseCase, SearchFlightsUseCase {

    private final FlightRepositoryPort flightRepositoryPort;

    public FlightService(FlightRepositoryPort flightRepositoryPort) {
        this.flightRepositoryPort = flightRepositoryPort;
    }

    @Override
    @Transactional
    public Flight reserveSeats(String flightNumber, Integer quantity) {
        Flight flight = flightRepositoryPort.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new RuntimeException("El vuelo no existe"));

        flight.reserveSeats(quantity);

        return flightRepositoryPort.guardar(flight);
    }

    @Override
    public List<Flight> searchFlights(SearchFlightsCriteria criteria) {
        return flightRepositoryPort.search(criteria);
    }
}
