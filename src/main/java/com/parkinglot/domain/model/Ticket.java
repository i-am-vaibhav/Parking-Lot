package com.parkinglot.domain.model;

import java.util.Date;

public record Ticket(Date entryTime, Date exitTime, Vehicle vehicle, ParkingSpot parkingSpot, Double amount, TicketStatus status) {
    public Ticket {
        if (entryTime == null) {
            throw new IllegalArgumentException("Entry Time cannot be null");
        }
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        if (parkingSpot == null) {
            throw new IllegalArgumentException("Parking Spot cannot be null");
        }
        if (!parkingSpot.vehicleType().equals(vehicle.vehicleType())){
            throw new IllegalArgumentException("Invalid vehicle or parking spot selection");
        }
        if (status == null) {
            throw new IllegalArgumentException("Ticket Status cannot be null");
        }
    }

    public Ticket updateAmount(double amount) {
        return new Ticket(entryTime(), exitTime(), vehicle(), parkingSpot(), amount, status());
    }

    public Ticket closeTicket() {
        return new Ticket(entryTime(), exitTime(), vehicle(), parkingSpot(), amount(), TicketStatus.CLOSED);
    }
}
