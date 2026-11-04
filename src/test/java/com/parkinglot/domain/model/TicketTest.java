package com.parkinglot.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    @Test
    void shouldCreateTicket() {
        Date entryTime = new Date();
        Vehicle vehicle = new Vehicle(1L, "CV73CB7232", VehicleType.CAR);
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, false);
        Ticket ticket = new Ticket(1L, entryTime, null, vehicle, parkingSpot, 0d, TicketStatus.ACTIVE);
        assertNotNull(ticket.amount());
        assertEquals(vehicle, ticket.vehicle());
        assertEquals(parkingSpot, ticket.parkingSpot());
        assertEquals(entryTime, ticket.entryTime());
        assertNull(ticket.exitTime());
    }

    @Test
    void shouldThrowExceptionWhenFieldsAreNull() {
        Date entryTime = new Date();
        Vehicle vehicle = new Vehicle(1L, "CV73CB7232", VehicleType.CAR);
        Vehicle vehicleTruck = new Vehicle(1L, "CV73CB7232", VehicleType.TRUCK);
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, false);

        var ieaNullEntryTime = assertThrows(IllegalArgumentException.class, () -> new Ticket(1L, null, null, vehicle, parkingSpot, 0d, TicketStatus.ACTIVE));
        assertEquals("Entry Time cannot be null", ieaNullEntryTime.getMessage());

        var iaeNullVehicle = assertThrows(IllegalArgumentException.class, () -> new Ticket(1L, entryTime, null, null, parkingSpot, 0d, TicketStatus.ACTIVE));
        assertEquals("Vehicle cannot be null", iaeNullVehicle.getMessage());

        var iaeNullParkingSpot = assertThrows(IllegalArgumentException.class, () -> new Ticket(1L, entryTime, null, vehicle, null, 0d, TicketStatus.ACTIVE));
        assertEquals("Parking Spot cannot be null", iaeNullParkingSpot.getMessage());

        var iaeInvalidSelection = assertThrows(IllegalArgumentException.class, () -> new Ticket(1L, entryTime, null, vehicleTruck, parkingSpot, 0d, TicketStatus.ACTIVE));
        assertEquals("Invalid vehicle or parking spot selection", iaeInvalidSelection.getMessage());

        var iaeNullStatus = assertThrows(IllegalArgumentException.class, () -> new Ticket(1L, entryTime, null, vehicle, parkingSpot, 0d, null));
        assertEquals("Ticket Status cannot be null", iaeNullStatus.getMessage());
    }

    @Test
    void shouldUpdateAmountWhenCalledUpdateAmountMethod() {
        Date entryTime = new Date();
        Vehicle vehicle = new Vehicle(1L, "CV73CB7232", VehicleType.CAR);
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, false);
        Ticket ticket = new Ticket(1L, entryTime, null, vehicle, parkingSpot, 0d, TicketStatus.ACTIVE);
        Ticket updateAmountTicket = ticket.updateAmount(100d);
        assertNotEquals(ticket.amount(), updateAmountTicket.amount());
    }

    @Test
    void shouldCloseTicketWhenCalledCloseTicketMethod() {
        Date entryTime = new Date();
        Vehicle vehicle = new Vehicle(1L, "CV73CB7232", VehicleType.CAR);
        ParkingSpot parkingSpot = new ParkingSpot(VehicleType.CAR, 1L, false);
        Ticket ticket = new Ticket(1L, entryTime, null, vehicle, parkingSpot, 0d, TicketStatus.ACTIVE);
        Ticket closedTicket = ticket.closeTicket();
        assertNotEquals(ticket.status(), closedTicket.status());
    }

}
