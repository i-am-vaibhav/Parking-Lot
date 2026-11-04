package com.parkinglot.domain.model;

import java.util.List;

public record ParkingFloor(Long floorNumber, List<ParkingSpot> parkingSpots) {
    public ParkingFloor {
        if (floorNumber == null) {
            throw new IllegalArgumentException("Floor Number cannot be null");
        }
        if (floorNumber < 0) {
            throw new IllegalArgumentException("Floor Number cannot be negative");
        }
        if (parkingSpots == null) {
            throw new IllegalArgumentException("Parking Spots cannot be null");
        }

        if (parkingSpots.isEmpty()) {
            throw new IllegalArgumentException("Parking Spots cannot be empty");
        }
    }
}
