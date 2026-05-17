package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "bookings")
public class BookingEntity {

    public BookingEntity(UUID id, String flightNumber, Long passengerId, String status, Integer seats) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
        this.status = status;
        this.seats = seats;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getSeats() { return seats;}
    public void setSeats(Integer seats) { this.seats = seats;}
    @Id
    private UUID id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "passenger_id", nullable = false)
    private Long passengerId;

    @Column(nullable = false)
    private String status; // Guardará "PENDING" inicialmente
    @Column(name = "seats") // Opcional, JPA lo hace por defecto
    private Integer seats;

    public BookingEntity() {}

}