package com.parkinglot.application.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntryResponse {
    private String ticketId;
    private String entryTime;
    private Long spotId;
    private String vehicleNumberPlate;
}
