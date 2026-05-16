package com.programandoenjava.flightservice.flight.domain.port.out;

import com.programandoenjava.flightservice.flight.domain.entities.Flight;
import com.programandoenjava.flightservice.flight.domain.port.in.SearchFlightsCriteria;

import java.util.List;
import java.util.Optional;

public interface FlightRepositoryPort {

    Flight guardar(Flight flight);

    Optional<Flight> findById(Long id);

    Optional<Flight> findByFlightNumber(String flightNumber);
    void deleteById(Long id);

    // NUEVO MÉTODO: Para que la base de datos busque los vuelos
    List<Flight> search(SearchFlightsCriteria criteria);
}