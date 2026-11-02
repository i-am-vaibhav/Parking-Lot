package com.parkinglot.domain.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void shouldCreateParkingLot(){
        ParkingFloor groundFloor = new ParkingFloor(0L, List.of(new ParkingSpot(VehicleType.CAR,1L, true), new ParkingSpot(VehicleType.CAR,2L, true)));
        ParkingFloor firstFloor = new ParkingFloor(1L, List.of(new ParkingSpot(VehicleType.CAR,1L, true), new ParkingSpot(VehicleType.CAR,2L, true)));
        ParkingLot parkingLot = new ParkingLot(1L,List.of(groundFloor, firstFloor));
        assertNotNull(parkingLot.id());
        assertNotNull(parkingLot.floors());
    }

    @Test
    void shouldThrowExceptionWhenInvalidValuesArePassed() {
        ParkingFloor groundFloor = new ParkingFloor(0L, List.of(new ParkingSpot(VehicleType.CAR,1L, true), new ParkingSpot(VehicleType.CAR,2L, true)));
        ParkingFloor firstFloor = new ParkingFloor(1L, List.of(new ParkingSpot(VehicleType.CAR,1L, true), new ParkingSpot(VehicleType.CAR,2L, true)));
        IllegalArgumentException iaeNullId = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(null, List.of(groundFloor, firstFloor)));
        assertEquals("Id cannot be null", iaeNullId.getMessage());
        IllegalArgumentException iaeNullFloors = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(1L, null));
        assertEquals("Floors cannot be null", iaeNullFloors.getMessage());
        IllegalArgumentException iaeEmptyFloors = assertThrows(IllegalArgumentException.class, () -> new ParkingLot(1L, List.of()));
        assertEquals("Floors cannot be empty", iaeEmptyFloors.getMessage());
    }
}
