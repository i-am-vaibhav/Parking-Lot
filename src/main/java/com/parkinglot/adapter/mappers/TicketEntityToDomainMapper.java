package com.parkinglot.adapter.mappers;

import com.parkinglot.adapter.out.persistence.entitiy.TicketEntity;
import com.parkinglot.adapter.out.persistence.entitiy.VehicleEntity;
import com.parkinglot.domain.model.Ticket;
import com.parkinglot.domain.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TicketEntityToDomainMapper {

    @Mapping(source = "parkingSpot.id", target = "parkingSpot.spotId")
    @Mapping(target = "updateAmount", ignore = true)
    @Mapping(target = "parkingSpot", source = "parkingSpot.parkingFloor", ignore = true)
    Ticket toTicket(TicketEntity entity);

    @Mapping(target = "parkingSpot.id", source = "parkingSpot.spotId")
    @Mapping(target = "parkingSpot.parkingFloor", ignore = true)
    TicketEntity toTicketEntity(Ticket ticket);

    VehicleEntity toVehicleEntity(Vehicle vehicle);

}
