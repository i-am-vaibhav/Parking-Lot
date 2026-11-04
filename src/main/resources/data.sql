-- Parking Lot
INSERT INTO parking_lot (id,name)
VALUES (1, 'Jai Bhawani ParkingLot');

-- Parking Floors
INSERT INTO parking_floor (id, floor_number, parking_lot_id)
VALUES
    (1, 1, 1),
    (2, 2, 1),
    (3, 3, 1);

-- Parking Spots - Floor 1
INSERT INTO parking_spot (id, vehicle_type, available, parking_floor_id)
VALUES
    (1, 'CAR', TRUE, 1),
    (2, 'CAR', TRUE, 1),
    (3, 'CAR', TRUE, 1),
    (4, 'BIKE', TRUE, 1),
    (5, 'BIKE', TRUE, 1),
    (6, 'TRUCK', TRUE, 1);

-- Parking Spots - Floor 2
INSERT INTO parking_spot (id, vehicle_type, available, parking_floor_id)
VALUES
    (7, 'CAR', TRUE, 2),
    (8, 'CAR', TRUE, 2),
    (9, 'BIKE', TRUE, 2),
    (10, 'BIKE', TRUE, 2),
    (11, 'TRUCK', TRUE, 2);

-- Parking Spots - Floor 3
INSERT INTO parking_spot (id, vehicle_type, available, parking_floor_id)
VALUES
    (12, 'CAR', TRUE, 3),
    (13, 'CAR', TRUE, 3),
    (14, 'BIKE', TRUE, 3),
    (15, 'TRUCK', TRUE, 3);