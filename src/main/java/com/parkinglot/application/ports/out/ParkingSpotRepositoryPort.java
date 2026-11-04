package com.parkinglot.application.ports.out;

import com.parkinglot.domain.model.ParkingSpot;

import java.util.Optional;

public interface ParkingSpotRepositoryPort {
    void save(ParkingSpot parkingSpot);

    Optional<ParkingSpot> findById(String spotId);

}
