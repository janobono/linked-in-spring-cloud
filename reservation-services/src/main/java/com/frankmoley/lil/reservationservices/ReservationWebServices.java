package com.frankmoley.lil.reservationservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationWebServices {

    private final ReservationRepository reservationRepository;

    public ReservationWebServices(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping
    public Iterable<Reservation> getAllReservations() {
        return this.reservationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable("id") long id) {
        return this.reservationRepository.findById(id).orElseThrow();
    }
}
