package com.parkinglot.application.model;

import lombok.Data;

@Data
public class EntryRequest {
    private Long parkingLotId;
    private String numberPlate;
    private String vehicleType;
    private String preferredSlotStrategy;
}
