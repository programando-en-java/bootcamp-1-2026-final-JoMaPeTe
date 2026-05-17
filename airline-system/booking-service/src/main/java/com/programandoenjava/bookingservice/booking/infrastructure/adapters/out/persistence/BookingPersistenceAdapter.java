package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingId;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingStatus;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.PassengerId;
import com.programandoenjava.bookingservice.booking.domain.port.out.BookingRepositoryPort;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity.BookingEntity;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.repository.BookingJpaRepository;

import java.util.Optional;
import java.util.UUID;


public class BookingPersistenceAdapter implements BookingRepositoryPort {

    private final BookingJpaRepository jpaRepository;

    public BookingPersistenceAdapter(BookingJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Booking save(Booking booking) {
        // Convertimos dominio -> entidad
        BookingEntity entity = new BookingEntity(
            booking.getId().value(),
            booking.getFlightNumber().value(),
            booking.getPassengerId().value(),
            booking.getStatus().name(),
                booking.getSeats()
        );
        
        BookingEntity savedEntity = jpaRepository.save(entity);
        
        // Devolvemos el objeto de dominio
        return new Booking(
                new BookingId(savedEntity.getId()),              // Envolvemos el UUID
                new FlightNumber(savedEntity.getFlightNumber()),  // Envolvemos el String
                new PassengerId(savedEntity.getPassengerId()),    // Envolvemos el Long
                BookingStatus.valueOf(savedEntity.getStatus()),
                savedEntity.getSeats()
        );
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        // 1. Buscamos en JPA (devuelve un Optional<BookingEntity>)
        return jpaRepository.findById(id)
                // 2. Si lo encuentra, lo mapeamos (traducimos) a la entidad de Dominio
                .map(entity -> new Booking(
                        new BookingId(entity.getId()),
                        new FlightNumber(entity.getFlightNumber()),
                        new PassengerId(entity.getPassengerId()),
                        // Usamos el constructor/estado adecuado
                        BookingStatus.valueOf(entity.getStatus()),
                        entity.getSeats()
                ));
    }
}