package com.parkinglot.application.strategy;

import com.parkinglot.domain.model.ParkingSpot;
import com.parkinglot.domain.model.VehicleType;

import java.util.Optional;

public interface SpotAllocationStrategy {
    Optional<ParkingSpot> findSpot(Long parkingLotId, VehicleType vehicleType);
}
