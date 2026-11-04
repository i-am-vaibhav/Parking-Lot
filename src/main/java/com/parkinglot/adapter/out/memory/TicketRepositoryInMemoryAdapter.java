package com.parkinglot.adapter.out.memory;

import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.domain.model.Ticket;

import java.util.Optional;

public class TicketRepositoryInMemoryAdapter implements TicketRepositoryPort {

    private final ParkingLotINMemoryDB parkingLotINMemoryDB;

    public TicketRepositoryInMemoryAdapter(ParkingLotINMemoryDB parkingLotINMemoryDB) {
        this.parkingLotINMemoryDB = parkingLotINMemoryDB;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return parkingLotINMemoryDB.put(ticket);
    }

    @Override
    public Optional<Ticket> findByTicketId(Long ticketId) {
        return Optional.ofNullable(parkingLotINMemoryDB.getTickets().getOrDefault(ticketId, null));
    }
}
