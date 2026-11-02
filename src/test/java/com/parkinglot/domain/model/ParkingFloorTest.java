package com.parkinglot.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingFloorTest {

    @Test
    void shouldCreateParkingFloor() {
        ParkingSpot ps1 = new ParkingSpot(VehicleType.CAR, 1L, true);
        ParkingSpot ps2 = new ParkingSpot(VehicleType.BIKE, 1L, true);
        ParkingFloor parkingFloor = new ParkingFloor(1L, List.of(ps1,ps2));
        assertNotNull(parkingFloor.floorNumber());
        assertNotNull(parkingFloor.parkingSpots());
    }

    @Test
    void shouldThrowExceptionWhenInvalidValuesArePassed() {
        ParkingSpot ps1 = new ParkingSpot(VehicleType.CAR, 1L, true);
        ParkingSpot ps2 = new ParkingSpot(VehicleType.BIKE, 1L, true);
        IllegalArgumentException iaeNullFloorNumber = assertThrows(IllegalArgumentException.class, () -> new ParkingFloor(null, List.of(ps1, ps2)));
        IllegalArgumentException iaeNegativeFloorNumber = assertThrows(IllegalArgumentException.class, () -> new ParkingFloor(-1L, List.of(ps1, ps2)));
        IllegalArgumentException iaeNullParkingSpots = assertThrows(IllegalArgumentException.class, () -> new ParkingFloor(1L, null));
        IllegalArgumentException iaeEmptyParkingSpots = assertThrows(IllegalArgumentException.class, () -> new ParkingFloor(1L, List.of()));
        assertEquals("Floor Number cannot be null",iaeNullFloorNumber.getMessage());
        assertEquals("Floor Number cannot be negative",iaeNegativeFloorNumber.getMessage());
        assertEquals("Parking Spots cannot be null",iaeNullParkingSpots.getMessage());
        assertEquals("Parking Spots cannot be empty",iaeEmptyParkingSpots.getMessage());
    }

}
