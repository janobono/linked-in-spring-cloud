package com.frankmoley.lil.roomreservationservice;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    private final RestTemplate restTemplate;

    public RoomReservationWebService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public Iterable<RoomReservation> getRoomReservations() {
        List<Room> rooms = this.getAllRooms();
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

    private List<Room> getAllRooms() {
        ResponseEntity<List<Room>> roomResponse = this.restTemplate.exchange(
                "http://ROOMSERVICES/rooms",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Room>>() {
                }
        );
        return roomResponse.getBody();
    }
}
