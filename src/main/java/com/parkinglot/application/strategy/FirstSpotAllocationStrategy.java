package com.parkinglot.application.strategy;

import com.parkinglot.application.ports.out.ParkingFloorRepositoryPort;
import com.parkinglot.domain.model.ParkingSpot;
import com.parkinglot.domain.model.VehicleType;

import java.util.Optional;

public class FirstSpotAllocationStrategy implements SpotAllocationStrategy {

    private final ParkingFloorRepositoryPort parkingFloorRepository;

    public FirstSpotAllocationStrategy(ParkingFloorRepositoryPort parkingFloorRepository) {
        this.parkingFloorRepository = parkingFloorRepository;
    }

    @Override
    public Optional<ParkingSpot> findSpot(Long parkingLotId, VehicleType vehicleType) {
        var floors = parkingFloorRepository.findByParkingLotId(parkingLotId);
        return floors.stream()
                .flatMap(floor -> floor.parkingSpots().stream())
                .filter(parkingSpot -> parkingSpot.available() && parkingSpot.vehicleType().equals(vehicleType))
                .findFirst();
    }

}
