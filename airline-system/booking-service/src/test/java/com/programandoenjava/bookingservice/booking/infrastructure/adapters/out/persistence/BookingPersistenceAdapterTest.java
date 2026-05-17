package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingId;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingStatus;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.PassengerId;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity.BookingEntity;
import com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.repository.BookingJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingPersistenceAdapterTest {

    @Mock
    private BookingJpaRepository jpaRepository;

    @InjectMocks
    private BookingPersistenceAdapter adapter;

    @Test
    void save_ShouldMapAndReturnBooking() {
        // Arrange
        Booking domainBooking = new Booking(
                new BookingId(UUID.randomUUID()),
                new FlightNumber("IB123"),
                new PassengerId(1L),
                BookingStatus.PENDING,
                1,
                150L
        );

        BookingEntity entity = new BookingEntity();
        entity.setId(domainBooking.getId().value());
        entity.setFlightNumber("IB123");
        entity.setStatus("PENDING");
        entity.setPassengerId(1L);
        entity.setSeats(1);
        when(jpaRepository.save(any(BookingEntity.class))).thenReturn(entity);

        // Act
        Booking result = adapter.save(domainBooking);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getPassengerId().value()).isEqualTo(1L);
        verify(jpaRepository).save(any(BookingEntity.class)); // Asegúrate de llamar al método aquí
    }
}