package com.parkinglot.domain.model;

import java.util.List;

public record ParkingLot(Long id, List<ParkingFloor> floors) {
    public ParkingLot {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
        if (floors == null) {
            throw new IllegalArgumentException("Floors cannot be null");
        }
        if (floors.isEmpty()) {
            throw new IllegalArgumentException("Floors cannot be empty");
        }
    }
}
