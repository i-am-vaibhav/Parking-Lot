package com.parkinglot.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void shouldCreateVehicle() {
        // Standard Number Plate
        Vehicle v = new Vehicle("CB01BG3456", VehicleType.CAR);
        assertNotNull(v.vehicleType());
        assertNotNull(v.numberPlate());

        // BH Number Plate
        Vehicle bh = new Vehicle("26BH3456AA", VehicleType.CAR);
        assertNotNull(bh.vehicleType());
        assertNotNull(bh.numberPlate());
    }

    @Test
    void shouldVehicleThrowExceptionWhenInvalidValuesArePassed() {
        IllegalArgumentException illegalArgumentExceptionNullNumberPlate = assertThrows(IllegalArgumentException.class, () -> new Vehicle(null, VehicleType.CAR));
        IllegalArgumentException illegalArgumentExceptionNullVehicleType = assertThrows(IllegalArgumentException.class, () -> new Vehicle("CG12CH8788", null));
        IllegalArgumentException illegalArgumentExceptionBlankNumberPlate = assertThrows(IllegalArgumentException.class, () -> new Vehicle("", VehicleType.CAR));
        assertEquals("Number Plate cannot be null or empty", illegalArgumentExceptionNullNumberPlate.getMessage());
        assertEquals("Number Plate cannot be null or empty", illegalArgumentExceptionBlankNumberPlate.getMessage());
        assertEquals("Vehicle Type cannot be null", illegalArgumentExceptionNullVehicleType.getMessage());

        IllegalArgumentException iaeInvalidNumberPlate = assertThrows(IllegalArgumentException.class, () -> new Vehicle("H889HS8788", VehicleType.CAR));
        assertEquals("Invalid Number Plate", iaeInvalidNumberPlate.getMessage());
    }

}
