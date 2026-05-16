package com.programandoenjava.bookingservice.booking.infrastructure.adapters.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "flight-service", url = "${external.services.flight-service.url}")
public interface FlightClient {

    @GetMapping("/api/v1/flights/{flightNumber}")
    FlightExternalDto getFlightByNumber(@PathVariable("flightNumber") String flightNumber);

    @PostMapping("/api/v1/flights/{flightNumber}/reserve")
    void updateFlightSeats(@PathVariable("flightNumber") String flightNumber, @RequestParam("quantity") int quantity);
}

// DTO interno para mapear la respuesta del flight-service
record FlightExternalDto(String flightNumber, int availableSeats) {}