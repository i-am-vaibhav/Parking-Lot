package com.parkinglot.application.service;

import com.parkinglot.application.exception.ParkingSpotUnavailableException;
import com.parkinglot.application.model.EntryRequest;
import com.parkinglot.application.model.EntryResponse;
import com.parkinglot.application.ports.in.EntryUseCase;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.application.strategy.SpotAllocationStrategy;
import com.parkinglot.domain.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

public class EntryUseCaseService implements EntryUseCase {

    private final TicketRepositoryPort ticketRepository;

    private final ParkingSpotRepositoryPort parkingSpotRepository;

    private final Map<String, SpotAllocationStrategy> spotAllocationStrategyMap;

    public EntryUseCaseService(
            TicketRepositoryPort ticketRepository, ParkingSpotRepositoryPort parkingSpotRepository,
            Map<String, SpotAllocationStrategy> spotAllocationStrategyMap) {
        this.ticketRepository = ticketRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.spotAllocationStrategyMap = spotAllocationStrategyMap;
    }

    @Override
    @Transactional
    public EntryResponse entry(EntryRequest entryRequest) {
        Optional<Ticket> ticket = ticketRepository.activeTicketsByVehicleNumber(entryRequest.getNumberPlate());
        if (ticket.isEmpty()){
            var spotAllocationStrategy = spotAllocationStrategyMap
                    .getOrDefault(
                            entryRequest.getPreferredSlotStrategy(),
                            spotAllocationStrategyMap.get("First"));
            ParkingSpot spot = spotAllocationStrategy
                    .findSpot(entryRequest.getParkingLotId(), VehicleType.valueOf(entryRequest.getVehicleType()))
                    .orElseThrow(() -> new ParkingSpotUnavailableException("Parking Spot Unavailable")).blockParkingSpot();
            Vehicle vehicle = new Vehicle(null, entryRequest.getNumberPlate(), VehicleType.valueOf(entryRequest.getVehicleType()));
            Ticket newTicket = new Ticket(null, new Date(), null, vehicle, spot, 0d, TicketStatus.ACTIVE);
            parkingSpotRepository.save(spot);
            ticket = Optional.of(ticketRepository.save(newTicket));
        }
        Ticket processedTicket = ticket.get();
        return EntryResponse.builder()
                .entryTime(processedTicket.entryTime().toString())
                .ticketId(processedTicket.id().toString())
                .spotId(processedTicket.parkingSpot().spotId())
                .vehicleNumberPlate(processedTicket.vehicle().numberPlate())
                .build();
    }
}
