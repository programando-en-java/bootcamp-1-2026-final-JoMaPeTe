package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingId;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingStatus;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.PassengerId;
import com.programandoenjava.bookingservice.booking.domain.port.out.BookingRepositoryPort;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity.BookingEntity;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.repository.BookingJpaRepository;
import org.springframework.stereotype.Component;


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
            booking.getStatus().name()
        );
        
        BookingEntity savedEntity = jpaRepository.save(entity);
        
        // Devolvemos el objeto de dominio
        return new Booking(
                new BookingId(savedEntity.getId()),              // Envolvemos el UUID
                new FlightNumber(savedEntity.getFlightNumber()),  // Envolvemos el String
                new PassengerId(savedEntity.getPassengerId()),    // Envolvemos el Long
                BookingStatus.valueOf(savedEntity.getStatus())
        );
    }
}