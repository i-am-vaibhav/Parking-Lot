package com.parkinglot.domain.model;

public record ParkingSpot(VehicleType vehicleType, Long floorNumber, Boolean available) {
    public ParkingSpot {
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle Type cannot be null");
        }
        if (floorNumber == null) {
            throw new IllegalArgumentException("Floor Number cannot be null");
        }
        if (floorNumber < 0) {
            throw new IllegalArgumentException("Floor Number cannot be negative value");
        }
        if (available == null) {
            throw new IllegalArgumentException("Available cannot be null");
        }
    }

    public ParkingSpot blockParkingSpot() {
        return new ParkingSpot(vehicleType(), floorNumber(), false);
    }

    public ParkingSpot unblockParkingSpot() {
        return new ParkingSpot(vehicleType(), floorNumber(), true);
    }
}
