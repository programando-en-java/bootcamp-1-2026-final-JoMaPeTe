package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id
    private UUID id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(nullable = false)
    private String status; // Guardará "PENDING" inicialmente

    // Constructores, Getters y Setters
    public BookingEntity() {}
    
    // ... (omitidos por brevedad)
}