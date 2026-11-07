package com.parkinglot.adapter.out.persistence;

import com.parkinglot.adapter.mappers.TicketEntityToDomainMapper;
import com.parkinglot.adapter.out.persistence.repository.SpringDataTicketRepository;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.domain.model.Ticket;
import com.parkinglot.domain.model.TicketStatus;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

public class TicketRepositoryAdapter implements TicketRepositoryPort {

    private final TicketEntityToDomainMapper mapper = Mappers.getMapper(TicketEntityToDomainMapper.class);

    private final SpringDataTicketRepository springDataTicketRepository;

    public TicketRepositoryAdapter(SpringDataTicketRepository springDataTicketRepository) {
        this.springDataTicketRepository = springDataTicketRepository;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return mapper.toTicket(springDataTicketRepository.save(mapper.toTicketEntity(ticket)));
    }

    @Override
    public Optional<Ticket> findByTicketId(Long ticketId) {
        return springDataTicketRepository.findById(ticketId).map(mapper::toTicket);
    }

    @Override
    public Optional<Ticket> activeTicketsByVehicleNumber(String vehicleNumber) {
        if (vehicleNumber == null || vehicleNumber.isBlank()) {
            throw new IllegalArgumentException("Vehicle Number cannot be null or empty");
        }
        return springDataTicketRepository.findByVehicle_NumberPlateAndStatus(vehicleNumber, TicketStatus.ACTIVE).map(mapper::toTicket);
    }

}
