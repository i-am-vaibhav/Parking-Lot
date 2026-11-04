package com.parkinglot.adapter.out.persistence.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "PARKING_FLOOR")
public class ParkingFloorEntity {
    @Id
    private Long id;

    @Column(name = "floor_number")
    private Long floorNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_lot_id", nullable = false)
    private ParkingLotEntity parkingLot;

    @OneToMany(
            mappedBy = "parkingFloor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ParkingSpotEntity> parkingSpots = new ArrayList<>();

}
