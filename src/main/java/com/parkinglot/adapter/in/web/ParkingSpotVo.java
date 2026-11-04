package com.parkinglot.adapter.in.web;

import com.parkinglot.domain.model.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSpotVo {
    private Long id;
    private VehicleType vehicleType;
    private Boolean available;
}
