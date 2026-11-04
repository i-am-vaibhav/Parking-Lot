package com.parkinglot.application.service;

import com.parkinglot.application.exception.TicketNotFoundException;
import com.parkinglot.application.model.ExitResponse;
import com.parkinglot.application.ports.in.ExitUseCase;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.application.strategy.FareCalculationStrategy;
import com.parkinglot.domain.model.Ticket;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

public class ExitUseCaseService implements ExitUseCase {

    private final TicketRepositoryPort ticketRepository;
    private final ParkingSpotRepositoryPort parkingSpotRepository;
    private final Map<String, FareCalculationStrategy> fareCalculationStrategyMap;

    public ExitUseCaseService(TicketRepositoryPort ticketRepository, ParkingSpotRepositoryPort parkingSpotRepository, Map<String, FareCalculationStrategy> fareCalculationStrategyMap) {
        this.ticketRepository = ticketRepository;
        this.parkingSpotRepository = parkingSpotRepository;
        this.fareCalculationStrategyMap = fareCalculationStrategyMap;
    }

    @Override
    public ExitResponse exit(Long ticketId) {
        FareCalculationStrategy fareCalculationStrategy = fareCalculationStrategyMap
                .get(resolvePricingStrategy());
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with " + ticketId));
        ticket = ticket.updateAmount(
                fareCalculationStrategy.calculate(ticket.entryTime(),
                        ticket.vehicle().vehicleType())).closeTicket();
        parkingSpotRepository.save(ticket.parkingSpot().unblockParkingSpot());
        Ticket savedTicket = ticketRepository.save(ticket);
        return ExitResponse.builder()
                .exitTime(savedTicket.exitTime())
                .amount(ticket.amount())
                .ticketId(ticketId)
                .entryTime(ticket.entryTime())
                .build();
    }

    private String resolvePricingStrategy() {
        DayOfWeek today = LocalDate.now().getDayOfWeek();

        if (today == DayOfWeek.SATURDAY ||
                today == DayOfWeek.SUNDAY) {
            return "Weekend";
        }

        return "Normal";
    }
}
