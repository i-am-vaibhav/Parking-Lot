package com.parkinglot.adapter.mappers;

import com.parkinglot.adapter.out.persistence.entitiy.ParkingFloorEntity;
import com.parkinglot.adapter.out.persistence.entitiy.ParkingSpotEntity;
import com.parkinglot.domain.model.ParkingFloor;
import com.parkinglot.domain.model.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ParkingFloorEntityToDomainMapper {

    @Mapping(source = "parkingSpots", target = "parkingSpots")
    ParkingFloor toParkingFloor(ParkingFloorEntity floorEntity);

    @Mapping(source = "id",target = "spotId")
    ParkingSpot toParkingSpot(ParkingSpotEntity parkingSpotEntity);
}
