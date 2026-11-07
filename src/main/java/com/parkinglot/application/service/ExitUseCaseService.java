package com.parkinglot.application.service;

import com.parkinglot.application.exception.TicketNotFoundException;
import com.parkinglot.application.model.ExitResponse;
import com.parkinglot.application.ports.in.ExitUseCase;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.application.strategy.FareCalculationStrategy;
import com.parkinglot.domain.model.Ticket;
import com.parkinglot.domain.model.TicketStatus;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    @Transactional
    public ExitResponse exit(Long ticketId) {
        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found with " + ticketId));
        if (ticket.status().equals(TicketStatus.ACTIVE)){
            FareCalculationStrategy fareCalculationStrategy = fareCalculationStrategyMap
                    .get(resolvePricingStrategy(ticket.entryTime()));
            ticket = ticket.updateAmount(
                    fareCalculationStrategy.calculate(ticket.entryTime(),
                            ticket.vehicle().vehicleType())).closeTicket();
            parkingSpotRepository.save(ticket.parkingSpot().unblockParkingSpot());
            ticket = ticketRepository.save(ticket);
        }
        return ExitResponse.builder()
                .exitTime(ticket.exitTime())
                .amount(ticket.amount())
                .ticketId(ticketId)
                .entryTime(ticket.entryTime())
                .build();
    }

    private String resolvePricingStrategy(Date date) {
        DayOfWeek today = LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()).getDayOfWeek();

        if (today == DayOfWeek.SATURDAY ||
                today == DayOfWeek.SUNDAY) {
            return "Weekend";
        }

        return "Normal";
    }
}
