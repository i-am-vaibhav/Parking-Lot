package com.parkinglot.domain.model;

public record ParkingSpot(VehicleType vehicleType, Long spotId, Boolean available) {
    public ParkingSpot {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle Type cannot be null");
        }
        if (spotId == null) {
            throw new IllegalArgumentException("Spot Id cannot be null");
        }
        if (spotId < 0) {
            throw new IllegalArgumentException("Spot Id cannot be negative value");
        }
        if (available == null) {
            throw new IllegalArgumentException("Available cannot be null");
        }
    }

    public ParkingSpot blockParkingSpot() {
        return new ParkingSpot(vehicleType(), spotId(), false);
    }

    public ParkingSpot unblockParkingSpot() {
        return new ParkingSpot(vehicleType(), spotId(), true);
    }
}
