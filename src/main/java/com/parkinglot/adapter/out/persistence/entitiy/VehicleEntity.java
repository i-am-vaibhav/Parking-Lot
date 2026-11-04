package com.parkinglot.adapter.out.persistence.entitiy;

import com.parkinglot.domain.model.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "VEHICLE")
public class VehicleEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number_plate")
    private String numberPlate;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

}
