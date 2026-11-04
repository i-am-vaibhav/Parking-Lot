package com.parkinglot.application.service;

import com.parkinglot.application.exception.ParkingSpotUnavailableException;
import com.parkinglot.application.model.EntryRequest;
import com.parkinglot.application.model.EntryResponse;
import com.parkinglot.application.ports.in.EntryUseCase;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.application.strategy.SpotAllocationStrategy;
import com.parkinglot.domain.model.*;

import java.util.Date;
import java.util.Map;

public class EntryUseCaseService implements EntryUseCase {

    public static final String FIRST =
            "First";

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
    public EntryResponse entry(EntryRequest entryRequest) {
        var spotAllocationStrategy = spotAllocationStrategyMap
                .getOrDefault(
                        entryRequest.getPreferredSlotStrategy(),
                        spotAllocationStrategyMap.get(FIRST));
        ParkingSpot spot = spotAllocationStrategy
                .findSpot(entryRequest.getParkingLotId(), VehicleType.valueOf(entryRequest.getVehicleType()))
                .orElseThrow(() -> new ParkingSpotUnavailableException("Parking Spot Unavailable")).blockParkingSpot();
        Vehicle vehicle = new Vehicle(null, entryRequest.getNumberPlate(), VehicleType.valueOf(entryRequest.getVehicleType()));
        Ticket ticket = new Ticket(null, new Date(), null, vehicle, spot, 0d, TicketStatus.ACTIVE);
        parkingSpotRepository.save(spot);
        Ticket savedTicket = ticketRepository.save(ticket);

        return EntryResponse.builder()
                .entryTime(savedTicket.entryTime().toString())
                .ticketId(savedTicket.id().toString())
                .spotId(spot.spotId())
                .vehicleNumberPlate(vehicle.numberPlate())
                .build();
    }
}
