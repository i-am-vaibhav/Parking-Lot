package com.parkinglot.adapter.out.memory;

import com.parkinglot.domain.model.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ParkingLotINMemoryDB {

    @Getter
    private final ParkingLot parkingLot;

    @Getter
    private final Map<Long, Ticket> tickets;

    private final AtomicLong ticketId = new AtomicLong(1);

    public ParkingLotINMemoryDB() {
        List<ParkingFloor> floors = new ArrayList<>();
        long j = 0;
        for (long i = 0; i < 2; i++) {
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            parkingSpots.add(new ParkingSpot(VehicleType.CAR, ++j, true));
            parkingSpots.add(new ParkingSpot(VehicleType.CAR, ++j, true));
            parkingSpots.add(new ParkingSpot(VehicleType.BIKE, ++j, true));
            parkingSpots.add(new ParkingSpot(VehicleType.BIKE, ++j, true));
            parkingSpots.add(new ParkingSpot(VehicleType.TRUCK, ++j, true));
            parkingSpots.add(new ParkingSpot(VehicleType.TRUCK, ++j, true));
            floors.add(new ParkingFloor(i, parkingSpots));
        }
        this.parkingLot = new ParkingLot(1L,"MyParkingLot",  floors);
        tickets = new HashMap<>();
    }

    public Ticket put(Ticket ticket) {
        Long id = ticket.id();
        if (id != null && tickets.containsKey(ticket.id())) {
            tickets.remove(id);
            tickets.put(id, ticket);
            return ticket;
        }
        id = ticketId.getAndIncrement();
        Ticket updatedTicket = new Ticket(id, ticket.entryTime(), ticket.exitTime(), ticket.vehicle(), ticket.parkingSpot(), ticket.amount(), ticket.status());
        tickets.put(id, updatedTicket);
        return updatedTicket;
    }

}
