package com.parkinglot.adapter.out.persistence.repository;

import com.parkinglot.adapter.out.persistence.entitiy.TicketEntity;
import com.parkinglot.domain.model.Ticket;
import com.parkinglot.domain.model.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataTicketRepository extends JpaRepository<TicketEntity, Long> {

    Optional<Ticket> findByVehicle_NumberPlateAndStatus(String vehicleNumber, TicketStatus ticketStatus);

}
