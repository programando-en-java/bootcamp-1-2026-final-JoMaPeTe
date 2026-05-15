package com.programandoenjava.flightservice.flight.infrastructure.adapters.out.persistence.repository;

import com.programandoenjava.flightservice.flight.infrastructure.adapters.out.persistence.entity.FlightEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataFlightRepository extends JpaRepository<FlightEntity, Long> {

    // 1. Tu estilo: Lock Pesimista sobrescribiendo el método base
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Override
    Optional<FlightEntity> findById(Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FlightEntity> findByFlightNumber(String flightNumber);

    // 2. Magia de Spring Data: El nombre del método crea la consulta automáticamente
    List<FlightEntity> findByOriginAndDestinationAndDepartureTimeBetween(
            String origin,
            String destination,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );
    List<FlightEntity> findByOriginAndDestinationAndDepartureTimeGreaterThanEqual(
            String origin,
            String destination,
            LocalDateTime time
    );
}