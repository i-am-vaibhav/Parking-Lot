package com.parkinglot.adapter.out.memory;

import com.parkinglot.application.ports.out.ParkingSpotRepositoryPort;
import com.parkinglot.domain.model.ParkingSpot;

import java.util.List;
import java.util.Optional;

public class ParkingSpotRepositoryInMemoryAdapter implements ParkingSpotRepositoryPort {

    private final ParkingLotINMemoryDB parkingLotINMemoryDB;

    public ParkingSpotRepositoryInMemoryAdapter(ParkingLotINMemoryDB parkingLotINMemoryDB) {
        this.parkingLotINMemoryDB = parkingLotINMemoryDB;
    }

    @Override
    public void save(ParkingSpot parkingSpot) {
        parkingLotINMemoryDB.getParkingLot()
                .floors()
                .stream()
                .filter(floor -> floor.parkingSpots().stream()
                        .anyMatch(spot -> spot.spotId().equals(parkingSpot.spotId())))
                .findFirst()
                .ifPresent(floor -> {
                    List<ParkingSpot> spots = floor.parkingSpots();

                    for (int i = 0; i < spots.size(); i++) {
                        if (spots.get(i).spotId().equals(parkingSpot.spotId())) {
                            spots.set(i, parkingSpot);
                            break;
                        }
                    }
                });
    }

    @Override
    public Optional<ParkingSpot> findById(String spotId) {
        Long id = Long.valueOf(spotId);

        return parkingLotINMemoryDB.getParkingLot()
                .floors()
                .stream()
                .flatMap(floor -> floor.parkingSpots().stream())
                .filter(spot -> spot.spotId().equals(id))
                .findFirst();
    }
}
