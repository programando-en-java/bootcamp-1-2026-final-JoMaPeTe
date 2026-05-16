package com.programandoenjava.bookingservice.booking.domain.entities;

import java.util.UUID;

public class Booking {
    private UUID id;
    private String flightNumber;
    private Long passengerId;
    private String status; // Aquí usaremos "PENDING" como pide la US-003

    public Booking(UUID id, String flightNumber, Long passengerId, String status) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.passengerId = passengerId;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Long passengerId) {
        this.passengerId = passengerId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

}