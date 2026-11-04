package com.parkinglot.adapter.out.memory;

import com.parkinglot.application.ports.out.ParkingFloorRepositoryPort;
import com.parkinglot.domain.model.ParkingFloor;

import java.util.List;

public class ParkingFloorRepositoryInMemoryAdapter implements ParkingFloorRepositoryPort {

    private final ParkingLotINMemoryDB parkingLotINMemoryDB;

    public ParkingFloorRepositoryInMemoryAdapter(ParkingLotINMemoryDB parkingLotINMemoryDB) {
        this.parkingLotINMemoryDB = parkingLotINMemoryDB;
    }

    @Override
    public List<ParkingFloor> findByParkingLotId(Long parkingLotId) {
        return parkingLotINMemoryDB.getParkingLot().floors();
    }
}
