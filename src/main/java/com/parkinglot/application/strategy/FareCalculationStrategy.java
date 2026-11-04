package com.parkinglot.application.strategy;

import com.parkinglot.domain.model.VehicleType;

import java.util.Date;
import java.util.Map;

public abstract class FareCalculationStrategy {

    private final Map<VehicleType, Double> baseRateMap;

    private final Double multiplier;

    protected FareCalculationStrategy(Double multiplier) {
        this.multiplier = multiplier;
        this.baseRateMap = Map.of(
                VehicleType.CAR, 80D,
                VehicleType.TRUCK, 150D,
                VehicleType.BIKE, 50D);
    }

    public Double calculate(Date entryTime, VehicleType vehicleType) {
        Date currentTime = new Date();

        long durationInMillis = currentTime.getTime() - entryTime.getTime();

        long hours = (long) Math.ceil(
                durationInMillis / (double) (60 * 60 * 1000)
        );

        return baseRateMap.get(vehicleType) * hours * multiplier;
    }

}
