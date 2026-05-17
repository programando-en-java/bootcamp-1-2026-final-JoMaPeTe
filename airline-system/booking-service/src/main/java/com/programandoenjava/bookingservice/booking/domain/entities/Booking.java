package com.programandoenjava.bookingservice.booking.domain.entities;

import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingId;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.BookingStatus;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.FlightNumber;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.PassengerId;

import java.util.UUID;
import com.programandoenjava.bookingservice.booking.domain.entities.vo.*;



public class Booking {
        private final BookingId id;
        private  FlightNumber flightNumber;
        private  PassengerId passengerId;
        private BookingStatus status;

        public Booking(BookingId id, FlightNumber flightNumber, PassengerId passengerId, BookingStatus status) {
            this.id = id;
            this.flightNumber = flightNumber;
            this.passengerId = passengerId;
            this.status = status;
        }

        // Getters...
        public BookingId getId() { return id; }
        public FlightNumber getFlightNumber() { return flightNumber; }
        public PassengerId getPassengerId() { return passengerId; }
        public BookingStatus getStatus() { return status; }
     // Aquí usaremos "PENDING" como pide la US-003

    public void setStatus(BookingStatus status) {
        this.status = status;
    }



    public void setPassengerId(PassengerId passengerId) {
        this.passengerId = passengerId;
    }



    public void setFlightNumber(FlightNumber flightNumber) {
        this.flightNumber = flightNumber;
    }

}