package com.parkinglot.adapter.out.persistence.entitiy;

import com.parkinglot.domain.model.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "PARKING_SPOT")
public class ParkingSpotEntity {

    @Id
    private Long id;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "available")
    private Boolean available = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parking_floor_id", nullable = false)
    private ParkingFloorEntity parkingFloor;

}
