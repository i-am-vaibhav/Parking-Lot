# Design Parking Lot

It has `N` number of floors, which can accommodate 3 types of vehicles: Bike, Car, and Truck.

When a vehicle enters through the entry gate, a parking ticket will be generated, and an available parking spot will be assigned to that vehicle according to its type.

When a vehicle exits through the exit gate, the parking fee should be calculated based on the entry time.

The design should allow us to add any other vehicle type in the future with minimal changes.

## Flow

                VEHICLE ENTRY
                     |
                     v
             Identify Vehicle Type
                     |
                     v
             ParkingLot.findSlots()
                     |
                     v
             Find Available Spot
                     |
             +-------+-------+
             |               |
          Available       Not Available
             |               |
             v               v
       Assign Spot       Reject Entry
             |
             v
       Generate Ticket
             |
             v
         VEHICLE PARKED
             |
             v
          VEHICLE EXIT
             |
             v
        Validate Ticket
             |
             v
        Record Exit Time
             |
             v
      Select Fare Strategy
             |
             v
       Calculate Parking Fee
             |
             v
        Complete Ticket
             |
             v
       Release Parking Spot

## Entities

### VehicleType

```java
enum VehicleType {
    CAR,
    TRUCK,
    BIKE
}
```

### FareCalculationStrategy

```java
interface FareCalculationStrategy {
    calculate(amount, ticket);
}
```

### Vehicle

```java
class Vehicle {
    String numberPlate;
    VehicleType vehicleType;
}
```

### Ticket

```java
class Ticket {
    String ticketId;
    Date startTime;
    Date endTime;
    Vehicle vehicle;
    ParkingSpot parkingSpot;
    double amount;
}
```

A `Ticket` has a `Vehicle` and a `ParkingSpot`.

### ParkingSpot

```java
class ParkingSpot {
    String spotId;
    VehicleType vehicleType;
    boolean isAvailable;
}
```

### ParkingFloor

```java
class ParkingFloor {
    int floorNumber;
    List<ParkingSpot> parkingSpots;
}
```

### ParkingLot

```java
class ParkingLot {
    List<ParkingFloor> floors;

    findSlots(type);

    entry(vehicle, spot);

    exit(ticket);
}
```