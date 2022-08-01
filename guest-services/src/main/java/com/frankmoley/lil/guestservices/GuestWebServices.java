package com.frankmoley.lil.guestservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guests")
public class GuestWebServices {

    private final GuestRepository guestRepository;

    public GuestWebServices(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @GetMapping
    public Iterable<Guest> getAllGuests() {
        return this.guestRepository.findAll();
    }

    @GetMapping("/{id}")
    public Guest getGuest(@PathVariable("id") long id) {
        return this.guestRepository.findById(id).orElseThrow();
    }
}
