package com.parkinglot.application.ports.out;

import com.parkinglot.domain.model.Ticket;

import java.util.Optional;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);

    Optional<Ticket> findByTicketId(Long ticketId);

    Optional<Ticket> activeTicketsByVehicleNumber(String vehicleNumber);
}
