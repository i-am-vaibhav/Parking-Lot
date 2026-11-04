package com.parkinglot.application.exception;

public class ParkingSpotUnavailableException extends RuntimeException {
    public ParkingSpotUnavailableException(String message) {
        super(message);
    }
}
