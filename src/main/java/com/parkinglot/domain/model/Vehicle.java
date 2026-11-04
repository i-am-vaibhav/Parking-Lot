package com.parkinglot.domain.model;

public record Vehicle(Long id, String numberPlate, VehicleType vehicleType) {

    // 1. Non BH Series Pattern (e.g., CG19GC9922)
    private static final String STANDARD_REGEX = "^[A-Z]{2}\\d{2}[A-HJ-NP-Z]{1,3}\\d{4}$";

    // 2. Bharat (BH) Series Pattern (e.g., 26BH3456AA)
    private static final String BH_REGEX = "^\\d{2}BH\\d{4}[A-Z]{1,2}$";

    public Vehicle {
        if (numberPlate == null || numberPlate.isBlank()) {
            throw new IllegalArgumentException("Number Plate cannot be null or empty");
        }
        if (!numberPlate.matches(STANDARD_REGEX) && !numberPlate.matches(BH_REGEX)) {
            throw new IllegalArgumentException("Invalid Number Plate");
        }
        if (vehicleType == null) {
            throw new IllegalArgumentException("Vehicle Type cannot be null");
        }
    }
}
