package com.parkinglot.adapter.mappers;

import com.parkinglot.adapter.out.persistence.entitiy.ParkingSpotEntity;
import com.parkinglot.domain.model.ParkingSpot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ParkingSpotEntityToDomainMapper {

    @Mapping(target = "id", source = "spotId")
    @Mapping(target = "parkingFloor", ignore = true)
    void updateEntity(
            ParkingSpot parkingSpot,
            @MappingTarget ParkingSpotEntity entity
    );

}
