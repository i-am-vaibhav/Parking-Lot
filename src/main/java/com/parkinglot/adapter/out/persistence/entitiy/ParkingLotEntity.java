package com.parkinglot.adapter.out.persistence.entitiy;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PARKING_LOT")
@Data
public class ParkingLotEntity {
    @OneToMany(
            mappedBy = "parkingLot",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<ParkingFloorEntity> parkingFloors = new ArrayList<>();
    @Id
    private Long id;

    @Column(name="name")
    private String name;
}
