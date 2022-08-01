package com.frankmoley.lil.roomreservationservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationWebService {

    private GuestClient guestClient;
    private ReservationClient reservationClient;
    private RoomClient roomClient;

    @Autowired
    public void setGuestClient(GuestClient guestClient) {
        this.guestClient = guestClient;
    }

    @Autowired
    public void setReservationClient(ReservationClient reservationClient) {
        this.reservationClient = reservationClient;
    }

    @Autowired
    public void setRoomClient(RoomClient roomClient) {
        this.roomClient = roomClient;
    }

    @GetMapping
    public Iterable<RoomReservation> getRoomReservations(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        List<Room> rooms = roomClient.getAllRooms();
        Map<Long, RoomReservation> roomReservations = new HashMap<>();
        rooms.forEach(room -> {
                    RoomReservation roomReservation = new RoomReservation();
                    roomReservation.setRoomNumber(room.getRoomNumber());
                    roomReservation.setRoomName(room.getName());
                    roomReservation.setRoomId(room.getId());
                    roomReservations.put(room.getId(), roomReservation);
                }
        );
        List<Reservation> reservations = this.reservationClient.getAllReservations(date);
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservations.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = guestClient.getGuest(reservation.getGuestId());
            roomReservation.setGuestId(guest.getId());
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
        });
        return new ArrayList<>(roomReservations.values());
    }
}
