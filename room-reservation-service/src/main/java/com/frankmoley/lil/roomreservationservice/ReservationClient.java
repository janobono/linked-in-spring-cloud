package com.frankmoley.lil.roomreservationservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient("reservationservices")
public interface ReservationClient {

    @GetMapping("/reservations")
    List<Reservation> getAllReservations(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date);

    @GetMapping("/reservations/{id}")
    Reservation getReservation(@PathVariable("id") long id);
}
