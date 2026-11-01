package com.parkinglot.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingSpotTest {

    @Test
    void shouldCreateParkingSpot() {
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, true);
        assertNotNull(parkingSpot);
        assertNotNull(parkingSpot.vehicleType());
        assertNotNull(parkingSpot.available());
        assertNotNull(parkingSpot.floorNumber());
    }

    @Test
    void shouldNotCreateParkingSpotWhenAnyValueIsNull() {
        IllegalArgumentException iaeNullVehicleType = assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(null, 1L, Boolean.TRUE));
        assertEquals("Vehicle Type cannot be null", iaeNullVehicleType.getMessage());

        IllegalArgumentException iaeNullFloorNumber = assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(VehicleType.CAR, null, Boolean.TRUE));
        assertEquals("Floor Number cannot be null", iaeNullFloorNumber.getMessage());
        IllegalArgumentException iaeNegativeFloorNumber = assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(VehicleType.CAR, -1L, Boolean.TRUE));
        assertEquals("Floor Number cannot be negative value", iaeNegativeFloorNumber.getMessage());

        IllegalArgumentException iaeNullAvailable = assertThrows(IllegalArgumentException.class, () -> new ParkingSpot(VehicleType.CAR, 1L, null));
        assertEquals("Available cannot be null", iaeNullAvailable.getMessage());
    }

    @Test
    void shouldBlockParkingSpotWhenCalledBlockMethod() {
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, true);
        ParkingSpot blockedParkingSpot = parkingSpot.blockParkingSpot();
        assertNotEquals(parkingSpot.available(), blockedParkingSpot.available());
        assertFalse(blockedParkingSpot.available());
    }

    @Test
    void shouldUnblockParkingSpotWhenCalledUnblockMethod() {
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, false);
        ParkingSpot availableParkingSpot = parkingSpot.unblockParkingSpot();
        assertNotEquals(parkingSpot.available(), availableParkingSpot.available());
        assertTrue(availableParkingSpot.available());
    }

}
