package com.parkinglot.application.ports.in;

import com.parkinglot.domain.model.ParkingSpot;

import java.util.List;
import java.util.Map;

public interface FindAvailableSlotsUseCase {
    Map<Long, List<ParkingSpot>> findAvailableSlots(Long parkingLotId);
}
