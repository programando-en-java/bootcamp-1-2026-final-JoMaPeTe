package com.programandoenjava.flightservice.flight.infrastructure.adapters.out.persistence;

import com.programandoenjava.flightservice.flight.domain.entities.Flight;
import com.programandoenjava.flightservice.flight.domain.entities.vo.FlightNumber;
import com.programandoenjava.flightservice.flight.domain.entities.vo.FlightPrice;
import com.programandoenjava.flightservice.flight.domain.port.in.SearchFlightsCriteria;
import com.programandoenjava.flightservice.flight.domain.port.out.FlightRepositoryPort;
import com.programandoenjava.flightservice.flight.infrastructure.adapters.out.persistence.entity.FlightEntity;
import com.programandoenjava.flightservice.flight.infrastructure.adapters.out.persistence.repository.SpringDataFlightRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightJpaAdapter implements FlightRepositoryPort {

    private final SpringDataFlightRepository jpaRepository;

    public FlightJpaAdapter(SpringDataFlightRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Flight guardar(Flight domainFlight) {
        // 1. Traducimos de Dominio a JPA usando nuestro método auxiliar
        FlightEntity jpaEntity = toEntity(domainFlight);

        // 2. Guardamos en la base de datos usando Spring
        FlightEntity savedEntity = jpaRepository.save(jpaEntity);

        // 3. Traducimos lo guardado de vuelta al Dominio y lo retornamos
        return toDomain(savedEntity);
    }

    @Override
    public Optional<Flight> findById(Long id) {
        Optional<FlightEntity> flightInDB = jpaRepository.findById(id); // <-- AHORA USA EL MÉTODO NATIVO CON LOCK
        return flightInDB.map(this::toDomain);
    }


    @Override
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return jpaRepository.findByFlightNumber(flightNumber)
                .map(this::toDomain);
    }
    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public List<Flight> search(SearchFlightsCriteria criteria) {
        if (criteria.departureDate() != null) {
            // US-002: El pasajero seleccionó una fecha concreta
            LocalDateTime startOfDay = criteria.departureDate().atStartOfDay();
            LocalDateTime endOfDay = criteria.departureDate().atTime(23, 59, 59);

            return jpaRepository.findByOriginAndDestinationAndDepartureTimeBetween(
                            criteria.origin(), criteria.destination(), startOfDay, endOfDay)
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        } else {
            // US-001: El pasajero solo puso origen y destino. Mostramos vuelos futuros.
            return jpaRepository.findByOriginAndDestinationAndDepartureTimeGreaterThanEqual(
                            criteria.origin(), criteria.destination(), LocalDateTime.now())
                    .stream()
                    .map(this::toDomain)
                    .collect(Collectors.toList());
        }
    }

    // --- Métodos Privados de Traducción (Mappers) ---

    private Flight toDomain(FlightEntity entity) {
        return new Flight(
                entity.getId(),
                new FlightNumber(entity.getFlightNumber()),
                entity.getOrigin(),
                entity.getDestination(),
                entity.getDepartureTime(),
                new FlightPrice(entity.getPrice().longValue()),
                entity.getAvailableSeats()
        );
    }

    private FlightEntity toEntity(Flight domain) {
        FlightEntity entity = new FlightEntity();
        entity.setId(domain.getId());
        entity.setFlightNumber(domain.getFlightNumber());
        entity.setOrigin(domain.getOrigin());
        entity.setDestination(domain.getDestination());
        entity.setDepartureTime(domain.getDepartureTime());
        entity.setPrice(java.math.BigDecimal.valueOf(domain.getPrice()));
        entity.setAvailableSeats(domain.getAvailableSeats());
        return entity;
    }
}