package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.repository;

import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, UUID> {
}