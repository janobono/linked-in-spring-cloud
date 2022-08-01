package com.frankmoley.lil.roomreservationservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    private final RoomClient roomClient;

    public RoomReservationWebService(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    @GetMapping
    public Iterable<RoomReservation> getRoomReservations() {
        List<Room> rooms = roomClient.getAllRooms();
        return rooms.stream().map(
                room -> {
                    RoomReservation roomReservation = new RoomReservation();
                    roomReservation.setRoomNumber(room.getRoomNumber());
                    roomReservation.setRoomName(room.getName());
                    roomReservation.setRoomId(room.getId());
                    return roomReservation;
                }
        ).collect(Collectors.toList());
    }
}
