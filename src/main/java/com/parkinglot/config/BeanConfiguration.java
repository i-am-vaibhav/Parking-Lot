package com.parkinglot.config;

import com.parkinglot.adapter.out.memory.ParkingFloorRepositoryInMemoryAdapter;
import com.parkinglot.adapter.out.memory.ParkingLotINMemoryDB;
import com.parkinglot.adapter.out.memory.ParkingSpotRepositoryInMemoryAdapter;
import com.parkinglot.adapter.out.memory.TicketRepositoryInMemoryAdapter;
import com.parkinglot.adapter.out.persistence.ParkingFloorRepositoryAdapter;
import com.parkinglot.adapter.out.persistence.ParkingSpotRepositoryAdapter;
import com.parkinglot.adapter.out.persistence.TicketRepositoryAdapter;
import com.parkinglot.adapter.out.persistence.repository.SpringDataParkingFloorRepository;
import com.parkinglot.adapter.out.persistence.repository.SpringDataParkingSpotRepository;
import com.parkinglot.adapter.out.persistence.repository.SpringDataTicketRepository;
import com.parkinglot.application.ports.in.EntryUseCase;
import com.parkinglot.application.ports.in.ExitUseCase;
import com.parkinglot.application.ports.in.FindAvailableSlotsUseCase;
import com.parkinglot.application.ports.out.ParkingFloorRepositoryPort;
import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.application.ports.out.TicketRepositoryPort;
import com.parkinglot.application.service.EntryUseCaseService;
import com.parkinglot.application.service.ExitUseCaseService;
import com.parkinglot.application.service.FindAvailableSlotsUseCaseService;
import com.parkinglot.application.strategy.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
public class BeanConfiguration {
    @Bean("Normal")
    public FareCalculationStrategy normalFareCalculationStrategy() {
        return new NormalFareCalculationStrategy();
    }

    @Bean("Weekend")
    public FareCalculationStrategy weekendFareCalculationStrategy() {
        return new WeekendFareCalculationStrategy();
    }

    @Bean("First")
    public SpotAllocationStrategy firstSpotAllocationStrategy(ParkingFloorRepositoryPort parkingFloorRepositoryPort) {
        return new FirstSpotAllocationStrategy(parkingFloorRepositoryPort);
    }

    @Bean("LowestFloor")
    public SpotAllocationStrategy lowestFloorSpotAllocationStrategy(ParkingFloorRepositoryPort parkingFloorRepositoryPort) {
        return new LowestFloorSpotAllocationStrategy(parkingFloorRepositoryPort);
    }

    @Bean
    public EntryUseCase entryUseCase(
            TicketRepositoryPort ticketRepositoryPort,
            ParkingSpotRepositoryPort parkingSpotRepositoryPort,
            Map<String, SpotAllocationStrategy> spotAllocationStrategyMap) {
        return new EntryUseCaseService(ticketRepositoryPort, parkingSpotRepositoryPort, spotAllocationStrategyMap);
    }

    @Bean
    public ExitUseCase exitUseCase(
            TicketRepositoryPort ticketRepositoryPort,
            ParkingSpotRepositoryPort parkingSpotRepositoryPort,
            Map<String, FareCalculationStrategy> fareCalculationStrategyMap) {
        return new ExitUseCaseService(ticketRepositoryPort, parkingSpotRepositoryPort, fareCalculationStrategyMap);
    }

    @Bean
    public FindAvailableSlotsUseCase findAvailableSlotsUseCase(ParkingFloorRepositoryPort parkingFloorRepositoryPort) {
        return new FindAvailableSlotsUseCaseService(parkingFloorRepositoryPort);
    }

    // Adapter

    @Bean
    @Profile({"memory"})
    public ParkingLotINMemoryDB parkingLotINMemoryDB(){
        return new ParkingLotINMemoryDB();
    }

    @Bean
    @Profile({"memory"})
    public ParkingFloorRepositoryPort parkingFloorRepositoryInMemoryAdaptor(ParkingLotINMemoryDB parkingLotINMemoryDB){
        return new ParkingFloorRepositoryInMemoryAdapter(parkingLotINMemoryDB);
    }

    @Bean
    @Profile({"memory"})
    public ParkingSpotRepositoryPort parkingSpotRepositoryInMemoryAdaptor(ParkingLotINMemoryDB parkingLotINMemoryDB){
        return new ParkingSpotRepositoryInMemoryAdapter(parkingLotINMemoryDB);
    }

    @Bean
    @Profile({"memory"})
    public TicketRepositoryPort ticketRepositoryInMemoryAdaptor(ParkingLotINMemoryDB parkingLotINMemoryDB){
        return new TicketRepositoryInMemoryAdapter(parkingLotINMemoryDB);
    }

    @Bean
    @Profile({"jpa"})
    public ParkingFloorRepositoryPort parkingFloorRepositoryAdaptor(SpringDataParkingFloorRepository parkingFloorRepository){
        return new ParkingFloorRepositoryAdapter(parkingFloorRepository);
    }

    @Bean
    @Profile({"jpa"})
    public ParkingSpotRepositoryPort parkingSpotRepositoryAdaptor(SpringDataParkingSpotRepository parkingSpotRepository){
        return new ParkingSpotRepositoryAdapter(parkingSpotRepository);
    }

    @Bean
    @Profile({"jpa"})
    public TicketRepositoryPort ticketRepositoryAdaptor(SpringDataTicketRepository ticketRepository){
        return new TicketRepositoryAdapter(ticketRepository);
    }

}
