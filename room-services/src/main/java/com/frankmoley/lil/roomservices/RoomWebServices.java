package com.frankmoley.lil.roomservices;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms")
public class RoomWebServices {

    private final RoomRepository roomRepository;

    public RoomWebServices(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public Iterable<Room> getAllRooms() {
        return this.roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable("id") long id) {
        return this.roomRepository.findById(id).orElseThrow();
    }
}
