package com.programandoenjava.bookingservice.booking.application.services;

import com.programandoenjava.bookingservice.booking.domain.entities.Booking;
import com.programandoenjava.bookingservice.booking.domain.entities.enums.BookingStatus;
import com.programandoenjava.bookingservice.booking.domain.port.out.BookingRepositoryPort;
import com.programandoenjava.bookingservice.booking.infraestructure.feign.FlightClient; // Tu cliente Feign
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepositoryPort bookingRepository;

    @Mock
    private FlightClient flightClient; // Para verificar el vuelo en el otro microservicio

    @InjectMocks
    private BookingService bookingService;

    @Test
    @DisplayName("Should create a booking with PENDING status")
    void createBooking_ShouldStartAsPending() {
        // 1 Arrange
        String flightNumber = "IB123";
        Long passengerId = 45L;
        Booking mockBooking = new Booking(UUID.randomUUID(), flightNumber, passengerId, BookingStatus.PENDING);

        given(bookingRepository.save(any(Booking.class))).willReturn(mockBooking);

        // 2 Act
        Booking result = bookingService.createBooking(flightNumber, passengerId);

        // 3 Assert
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(BookingStatus.PENDING); // Criterio de aceptación 3
        assertThat(result.getFlightNumber()).isEqualTo(flightNumber);

        verify(bookingRepository).save(any(Booking.class));
    }
}