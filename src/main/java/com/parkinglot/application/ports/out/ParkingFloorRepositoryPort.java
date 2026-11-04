package com.parkinglot.application.ports.out;

import com.parkinglot.domain.model.ParkingFloor;

import java.util.List;

public interface ParkingFloorRepositoryPort {
    List<ParkingFloor> findByParkingLotId(Long parkingLotId);
}
