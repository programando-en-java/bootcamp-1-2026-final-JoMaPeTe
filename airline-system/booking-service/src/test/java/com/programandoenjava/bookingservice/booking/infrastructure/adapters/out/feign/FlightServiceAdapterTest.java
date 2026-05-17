package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FlightServiceAdapterTest {

    @Mock
    private FlightClient flightClient;

    @InjectMocks
    private FlightServiceAdapter adapter;

    @Test
    void reserveSeats_ShouldCallFeignClient() {
        // Act
        adapter.reserveSeats(new FlightNumber("IB123"), 2);

        // Assert
        verify(flightClient).updateFlightSeats(eq("IB123"), anyInt());
    }
}