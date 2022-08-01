package com.frankmoley.lil.reservationservices;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    Iterable<Reservation> findAllByDate(LocalDate date);
}
