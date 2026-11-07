package com.parkinglot.application.ports.out;

import com.parkinglot.domain.model.ParkingSpot;

public interface ParkingSpotRepositoryPort {

    void save(ParkingSpot parkingSpot);

}
